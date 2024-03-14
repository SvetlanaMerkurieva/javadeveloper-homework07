package org.example.elements;

public class ProjectsPrices {
    private int projectID;
    private int price;

    public ProjectsPrices(int projectID, int price) {
        this.projectID = projectID;
        this.price = price;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "projectID=" + projectID +
                ", price=" + price;
    }
}
