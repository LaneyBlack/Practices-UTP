/**
 * @author Reut Anton S24382
 */

package utp7_4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        executor.execute(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String argument;
                while (!executor.isShutdown())
                    try {
                        argument = reader.readLine().trim().toLowerCase();
                        switch (argument) {
                            case "-close":
                                System.out.println("Executor closed.");
                                executor.shutdown();
                                break;
                            case "-abort":
                                System.out.println("Executor aborted.");
                                System.exit(-1);
                                break;
                            case "-help":
                                System.out.println("Commands:\n" +
                                        "\t-close = closes the server, server is not accepting new clients and waits for task to be done)\n" +
                                        "\t-abort = extreme exit");
                                break;
                            default:
                                System.out.println("Wrong Command");
                                System.out.println("Use -help to see the commands list");
                                break;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
            }
        });
        results = new ArrayList<>();
        listenSocket(port);
    }

    public static void listenSocket(int port) {
        ServerSocket server = null;
        Socket client = null;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Could not listen");
            System.exit(-1);
        }
        System.out.println("Server listens on port: " + server.getLocalPort());
        while (!executor.isShutdown()) {
            try {
                client = server.accept();
                if (client.isConnected() && !executor.isShutdown()) {
                    System.out.println("Client " + client.getLocalAddress() + ":" + client.getLocalPort() + " is connected");
                    String result = " ";
                    executor.execute(new ServerTask<>(client, result));
                    results.add(result);
                }
            } catch (IOException e) {
                System.out.println("Accept failed");
                System.exit(-1);
            }
//            New Thread to every client
//            (new ServerThread<String>(client)).start();
        }
        System.out.println("End");
    }

    public static ExecutorService getExecutor() {
        return executor;
    }
}
