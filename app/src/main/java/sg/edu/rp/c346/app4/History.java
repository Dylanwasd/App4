package sg.edu.rp.c346.app4;

public class History {
    private int id;
    private String history;
    private int historyPoint;
    private String date;

    public History(int id, String history, int historyPoint, String date) {
        this.id = id;
        this.history = history;
        this.historyPoint = historyPoint;
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public int getId() {
        return id;
    }

    public String getHistory() {
        return history;
    }

    public int getHistoryPoint() {
        return historyPoint;
    }
}
