package utp7_4;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.FutureTask;

public class ServerThread<R> extends FutureTask<R> {
    private final Socket socket;
    private TaskState state;

    public ServerThread(Socket socket, R result) {
        super(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    String word = in.readLine(), result = "";
                    System.out.println(word);
                    Thread.sleep(7000);
                    for (int i = 0; i < 100; i++)
                        result += word;
                    System.out.println(result);
                    out.println(result);
                    out.println("Done");
                } catch (IOException e1) {
                    System.out.println("Run exception");
                    throw new RuntimeException();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Socket closing exception");
                    throw new RuntimeException();
                }
            }
        }, result);
        this.state = TaskState.CREATED;
        this.socket = socket;
    }
}
