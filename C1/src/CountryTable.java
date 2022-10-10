import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CountryTable {

    private String[] columnNames;
    private Object[][] data;

    public CountryTable(String sourceFileName) {
        data = new Object[(int) countLines(sourceFileName) - 1][4];
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

    @SuppressWarnings("resource")
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
        JTable jtable = new JTable(data, columnNames) {
            //return correct column class
            public Class<?> getColumnClass(int column) {
                if (column == 2)
                    return Integer.class;
                else if (column == 3)
                    return ImageIcon.class;
                return String.class;
            }

            //not editable
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //color of the population higher than 20 000
        jtable.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value != null && value.getClass() == Integer.class)
                    c.setForeground(((Integer) value) > 20000 ? Color.RED : Color.BLACK);
                return c;
            }
        });
        return jtable;
    }

}
