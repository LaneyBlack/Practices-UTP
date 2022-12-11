package utp7_4;

import utp7_3.TaskState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ServerTask<R> extends FutureTask<R> {
    private final Socket socket;
    private TaskState state;

    public ServerTask(Socket socket, R result) {
        super(new Callable<R>() {
            @Override
            public R call() {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    String word = in.readLine(), result = "";
                    System.out.println(word);
                    Thread.sleep(8000);
                    while (result.length() < 100)
                        result += word;
                    System.out.println("To Client" + result);
                    out.println(result);
                    out.println("Done");
                    word = in.readLine();
                    if (word.equals("abort"))
                        synchronized (Main.getExecutor()) {
                            Main.getExecutor().shutdown();
                        }
                } catch (IOException e1) {
                    System.out.println("Read exception");
                    throw new RuntimeException();
                } catch (InterruptedException e) {
                    System.out.println("Sleep exception");
                    throw new RuntimeException(e);
                }

                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Socket closing exception");
                    throw new RuntimeException();
                }
                return result;
            }
        });
        this.state = TaskState.CREATED;
        this.socket = socket;
    }
}
