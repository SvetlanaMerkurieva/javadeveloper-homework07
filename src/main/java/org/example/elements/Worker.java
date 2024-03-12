package org.example.elements;

import java.time.LocalDate;

public class Worker {
    private int id;
    private String name;
    private LocalDate birthday;
    private String level;
    private int salary;

    public Worker(int id, String name, LocalDate birthday, String level, int salary) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.level = level;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
