package sg.edu.rp.c346.app4;

import java.io.Serializable;

public class Person implements Serializable {
    private int id;
    private String name;
    private int pass;

    public Person(int id, String name, int pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }
}
