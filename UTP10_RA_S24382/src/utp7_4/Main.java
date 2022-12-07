/**
 * @author Reut Anton S24382
 */

package utp7_4;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static ExecutorService executor;

    private static ArrayList<String> results;

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("Wrong number of parameters:" +
                    "\nGot - " + args.length +
                    "\nExpected - 1");
        }
        //Server port
        int port = Integer.parseInt(args[0]);
        executor = Executors.newFixedThreadPool(8);
        results = new ArrayList<>();
        listenSocket(port, args[1]);
    }

    public static void listenSocket(int port, String argument) {
        ServerSocket server = null;
        Socket client = null;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Could not listen");
            System.exit(-1);
        }
        System.out.println("Server IP: " + server.getInetAddress());
        System.out.println("Server listens on port: " + server.getLocalPort());
        while (true) {
            try {
                client = server.accept();
                if (client.isConnected()) {
                    System.out.println("Client " + client.getLocalAddress() + ":" + client.getLocalPort() + " is connected");
                    String result = "";
                    executor.execute(new ServerThread<>(client, result));
                    results.add(result);
                }
            } catch (IOException e) {
                System.out.println("Accept failed");
                System.exit(-1);
            }
            if (argument.equals("abort")) {
                System.out.println("Aborting");
                Thread stopper = new Thread(() -> {
                    try {
                        Thread.sleep(10000);
                        synchronized (executor) {
                            System.out.println("Aborted");
                            executor.shutdown();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                stopper.start();
            }
//            New Thread to every client
//            (new ServerThread<String>(client)).start();
        }
    }
}
