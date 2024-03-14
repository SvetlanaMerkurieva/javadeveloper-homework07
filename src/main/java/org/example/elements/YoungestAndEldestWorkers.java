package org.example.elements;

import java.time.LocalDate;

public class YoungestAndEldestWorkers {
    private String type;
    private String name;
    private LocalDate birthday;

    public YoungestAndEldestWorkers(String type, String name, LocalDate birthday) {
        this.type = type;
        this.name = name;
        this.birthday = birthday;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday;
    }
}
