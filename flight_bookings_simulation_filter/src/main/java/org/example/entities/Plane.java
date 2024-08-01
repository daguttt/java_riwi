package org.example.entities;

public class Plane {
    private int id;
    private String model;
    private int capacity;

    public Plane(int id, String model, int capacity) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
    }

    public Plane(String model, int capacity) {
        this.model = model;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        var lines = new String[] {
                String.format("id: %d", getId()),
                String.format("model: %s", getModel()),
                String.format("capacity: %s", getCapacity()),
        };
        return String.join("\n", lines);
    }
}
