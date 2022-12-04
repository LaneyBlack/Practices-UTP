/**
 * @author Reut Anton S24382
 */

package utp7_3;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) {
        //ToDo get it done
        JFrame f = new JFrame("Table Example");
        ArrayList<Future> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String outcome = "";
            tasks.add(new FutureTask(new Runnable() {
                @Override
                public void run() {
                    String text = String.valueOf((char) ((int) (Math.random() * 33 + 1)));
                    String result = "";
                    int count = (int) (Math.random() * 1_000_000 + 1);
                    for (int i = 0; i < count; i++)
                        result += text;
                }
            }, outcome));
        }
        String data[][] = new String[10][4];
        for (int i = 0; i < 10; i++) {
            data[i][0] = tasks.get(i).toString();
            data[i][1] = tasks.get(i).isCancelled()?"Canceled":"Not Canceled";
            data[i][1] = String.valueOf(tasks.get(i).isCancelled());
        }
        String column[] = {"NAME", "STATUS", "INTERRUPT", "RESULT"};
        final JTable jt = new JTable(data, column);
        jt.setCellSelectionEnabled(true);
        ListSelectionModel select = jt.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String Data = null;
                int[] row = jt.getSelectedRows();
                int[] columns = jt.getSelectedColumns();
                for (int i = 0; i < row.length; i++) {
                    for (int j = 0; j < columns.length; j++) {
                        Data = (String) jt.getValueAt(row[i], columns[j]);
                    }
                }
                System.out.println("Table element selected is: " + Data);
            }
        });
        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
