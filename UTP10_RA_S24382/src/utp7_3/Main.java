/**
 * @author Reut Anton S24382
 */

package utp7_3;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {
    private static ExecutorService executor;

    public static void main(String[] args) {
        //ToDo get it done (I guess I don't like Swing
        JFrame f = new JFrame("Tasks List");
        f.setSize(1920, 1080);
        f.setDefaultCloseOperation(3);
        Container pane = f.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        ArrayList<MyTask> tasks = new ArrayList<>();
        executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            tasks.add(new MyTask("Task" + i, new Callable() {
                @Override
                public String call() {
                    String text = String.valueOf((char) ((int) (Math.random() * 33 + 1)));
                    String result = "";
                    int count = (int) (Math.random() * 1_000_000 + 1);
                    for (int i = 0; i < count; i++)
                        result += text;
                    return result;
                }
            }));
            executor.submit(tasks.get(i));
        }
        String data[][] = new String[10][4];
        for (int i = 0; i < 10; i++) {
            data[i][0] = tasks.get(i).toString();
            data[i][1] = tasks.get(i).isCancelled() ? "Canceled" : "Not Canceled";
            data[i][2] = tasks.get(i).getResult();
        }
        final JList jl = new JList(data);
        for (int i = 0; i < tasks.size(); i++) {
            JLabel result = new JLabel(tasks.get(i).getResult() + "; " + tasks.get(i).getState());
            jl.add(tasks.get(i).toString(), result);
        }
        jl.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton db = new JButton("Delete");
        db.setAlignmentX(Component.CENTER_ALIGNMENT);
        db.addActionListener(e -> executor.shutdown());
//        JScrollPane sp = new JScrollPane(jl);
        pane.add(jl);
        pane.add(db);
        f.setVisible(true);
    }
}
