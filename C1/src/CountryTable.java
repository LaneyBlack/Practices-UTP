import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CountryTable {

    private String[] columnNames;
    private Object[][] data;

    public CountryTable(String sourceFileName) {
        data = new Object[(int) countLines(sourceFileName)][4];
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFileName));
            String text;
            int index = 0;
            while ((text = bufferedReader.readLine()) != null) {
                String[] values = text.trim().split("\t");
                if (index != 0)
                    data[index - 1] = new Object[]{values[0], values[1], Integer.parseInt(values[2]), new ImageIcon("data/flags/" + values[3] + ".png")};
                else
                    columnNames = values;
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long countLines(String fileName) {
        Path path = Paths.get(fileName);
        long lines = 0;
        try {
            lines = Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public JTable create() {
        JTable table = new JTable(data, columnNames) {
            public Class getColumnClass(int column) {
                return (column == 2) ? Integer.class : (column == 3) ? ImageIcon.class : String.class;
            }

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setForeground(((Integer) value) > 20000 ? Color.RED : Color.BLACK);
                return c;
            }
        });
        return table;
    }

}
