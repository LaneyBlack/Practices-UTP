package utp7_3;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyTask extends FutureTask<String> {
    private String name;
    private String result;
    private TaskState state;

    public MyTask(String name,Callable<String> callable) {
        super(callable);
        this.name = name;
        state = TaskState.CREATED;
    }

    public String getName() {
        return name;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getResult() {
        return result;
    }
}
