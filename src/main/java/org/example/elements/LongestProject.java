package org.example.elements;

public class LongestProject {
    private int id;
    private int monthCount;

    public LongestProject(int id, int monthCount) {
        this.id = id;
        this.monthCount = monthCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", monthCount = " + monthCount;
    }

}
