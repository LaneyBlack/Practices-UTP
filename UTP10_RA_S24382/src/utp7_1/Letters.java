package utp7_1;

import java.util.ArrayList;

public class Letters {
    private boolean areRunning;
    private StringBuilder letter;
    private ArrayList<Thread> threads;
    private String text;

    public Letters(String text) {
        this.text = text;
        letter = new StringBuilder();
        threads = new ArrayList<>();
        for (int i = 0; i < text.length(); i++)
            threads.add(new Thread(() -> {
                String name = Thread.currentThread().getName();
                while (!Thread.interrupted() && areRunning) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (letter) {
                        letter.append(name.charAt(name.length() - 1));
                    }
                }
            }, "Thread " + text.charAt(i)));
    }

    public void runThreads() {
        areRunning = true;
        for (Thread thread : threads)
            thread.start();
    }

    public void interruptThreads() throws InterruptedException {
        areRunning = false;
        Thread.sleep(1000);
        text = letter.toString();
        System.out.println(text);
    }

    public ArrayList<Thread> getThreads() {
        return threads;
    }
}
