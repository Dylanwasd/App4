package sg.edu.rp.c346.app4;

public class Point {
    private int points;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public Point(int points, int id) {
        this.points = points;
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public int getId(){
        return id;
    }
}
