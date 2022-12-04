package utp7_2;

public class StringTask implements Runnable {
    private String text;
    private int count;
    private TaskState state;

    public StringTask(String text, int count) {
        this.text = text;
        this.count = count;
        this.state = TaskState.CREATED;
    }

    public void start() {
        if (state == TaskState.CREATED) {
            state = TaskState.RUNNING;
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        state = TaskState.RUNNING;
        String add = text;
        while (count > 1 && state == TaskState.RUNNING) {
            text = text + add;
            count--;
        }
        if (state == TaskState.RUNNING)
            state = TaskState.READY;
    }

    public void abort() {
        state = TaskState.ABORTED;
    }

    public boolean isDone() {
        return state == TaskState.READY || state == TaskState.ABORTED;
    }

    public TaskState getState() {
        return state;
    }

    public String getResult() {
        return text;
    }
}
