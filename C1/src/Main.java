import javax.swing.*;

public class Main {
    private JTable ctab;

    public void createTable(String countriesFileName) {
        ctab = new CountryTable(countriesFileName).create();
    }

    public void showGui() {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Countries table");
            f.add(new JScrollPane(ctab));
            f.pack();
            f.setLocationRelativeTo(null);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setVisible(true);
        });
    }

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.createTable("data/countries.txt");
            main.showGui();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Table creation failed, " + exc);
        }
    }
}