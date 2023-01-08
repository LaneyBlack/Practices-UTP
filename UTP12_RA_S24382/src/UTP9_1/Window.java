package UTP9_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

public class Window extends JFrame implements ActionListener {
    private ArrayList<Travel> travelList;
    private JPanel mainPanel;
    private JTable jTable;


    public Window(ArrayList<Travel> travelList) throws HeadlessException {
        this.travelList = travelList;
        //Frame creation
        setDefaultCloseOperation(3);
        setSize(600, 400);
        setTitle("Travel Data");
        mainPanel = new JPanel();

        String[] langOptions = {"en-GB", "pl-PL"};
        JComboBox<String> comboBox = new JComboBox(langOptions);
        comboBox.addActionListener(this);
        mainPanel.add(comboBox);
        jTable = new JTable();
        mainPanel.add(jTable);
        add(mainPanel);
    }

    public void showGUI() {
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
        String lang = (String) comboBox.getSelectedItem();
        String[] columns;
        Object[][] data = new Object[travelList.size()][];
        if (lang.equals("en-US"))
            columns = new String[]{"CCode", "Country", "DateOut", "DateBack", "Place", "Cost", "Currency"};
        else
            columns = new String[]{"CCode", "Kraj", "DataWyj", "DataWróc", "Mejsce", "Coszt", "Waluta"};
        for (int i = 0; i < travelList.size(); i++)
            data[i] = travelList.get(i).toParts(Locale.forLanguageTag(lang));
        jTable = new JTable(data, columns);
        mainPanel.remove(1);
        mainPanel.add(jTable);
        mainPanel.updateUI();
    }
}
