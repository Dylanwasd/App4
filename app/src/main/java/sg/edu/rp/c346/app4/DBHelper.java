package sg.edu.rp.c346.app4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "checkr.db";

    private static final String TABLE_TASK = "task";
    private static final String TABLE_REWARD = "reward";
    private static final String TABLE_POINT = "point";
    private static final String TABLE_HISTORY = "history";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableTask = "CREATE TABLE " + TABLE_TASK + "(id INTEGER PRIMARY KEY AUTOINCREMENT, task TEXT, point INTEGER)";
        String createTableReward = "CREATE TABLE " + TABLE_REWARD + "(id INTEGER PRIMARY KEY AUTOINCREMENT, reward TEXT, reward_point INTEGER)";
        String createTablePoint = "CREATE TABLE " + TABLE_POINT + "(id INTEGER PRIMARY KEY AUTOINCREMENT, total_point INTEGER)";
        String createTableHistory = "CREATE TABLE " + TABLE_HISTORY + "(id INTEGER PRIMARY KEY AUTOINCREMENT, history_reward TEXT, history_point INTEGER)";
        db.execSQL(createTableTask);
        db.execSQL(createTableReward);
        db.execSQL(createTablePoint);
        db.execSQL(createTableHistory);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REWARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POINT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }
    public void insertTask(String task, int point){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("task", task);
        values.put("point", point);
        db.insert(TABLE_TASK, null, values);
        db.close();
    }
    public void insertReward(String reward, int reward_point){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("reward", reward);
        values.put("reward_point", reward_point);
        db.insert(TABLE_REWARD, null, values);
        db.close();
    }
    public void insertHistory(Reward data){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("history_reward", data.getReward());
        values.put("history_point", data.getPoints());
        db.insert(TABLE_HISTORY, null, values);
        db.close();
    }
    public void insertPoint(int total_point){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("total_point", total_point);
        db.insert(TABLE_POINT, null, values);
        db.close();
    }
    public int deleteTask(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = "id" + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_TASK, condition, args);
        db.close();
        return result;
    }
    public int deleteReward(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = "id" + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_REWARD, condition, args);
        db.close();
        return result;
    }

    public int updatePoint(Point data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("total_point", data.getPoints());
        String condition = "id" + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_POINT, values, condition, args);
        db.close();
        return result;
    }

    public ArrayList<Task> getTask() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        String selectQuery = "SELECT id, task, point FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String task = cursor.getString(1);
                int point = cursor.getInt(2);
                Task obj = new Task(id, task, point);
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }

    public ArrayList<Reward> getReward() {
        ArrayList<Reward> rewards = new ArrayList<Reward>();
        String selectQuery = "SELECT id, reward, reward_point FROM " + TABLE_REWARD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String reward = cursor.getString(1);
                int reward_point = cursor.getInt(2);
                Reward obj = new Reward(id, reward, reward_point);
                rewards.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return rewards;
    }
    public ArrayList<History> getHistory() {
        ArrayList<History> history = new ArrayList<History>();
        String selectQuery = "SELECT id, history_reward, history_point FROM " + TABLE_HISTORY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String history_reward = cursor.getString(1);
                int history_point = cursor.getInt(2);
                History obj = new History(id, history_reward, history_point);
                history.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return history;
    }
    public ArrayList<Integer> getPointContent() {
        ArrayList<Integer> point = new ArrayList<Integer>();
        String selectQuery = "SELECT  total_point FROM " + TABLE_POINT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                point.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return point;
    }
    public ArrayList<Point> getPoint() {
        ArrayList<Point> points = new ArrayList<Point>();
        String selectQuery = "SELECT  id, total_point FROM " + TABLE_POINT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int point = cursor.getInt(1);
                Point obj = new Point(id, point);
                points.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return points;
    }





}
