package sg.edu.rp.c346.app4;

public class Reward {
    private int id;
    private String reward;
    private int points;

    public Reward(int id, String reward, int points) {
        this.id = id;
        this.reward = reward;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public String getReward() {
        return reward;
    }

    public int getPoints() {
        return points;
    }
}
