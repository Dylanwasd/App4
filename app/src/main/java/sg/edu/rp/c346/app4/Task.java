package sg.edu.rp.c346.app4;

public class Task {
    private String task;
    private int point;
    private int id;

    public int getId() {
        return id;
    }

    public Task(int id, String task, int point) {
        this.id = id;
        this.task = task;
        this.point = point;
    }

    public String getTask() {
        return task;
    }

    public int getPoint() {
        return point;
    }
}
