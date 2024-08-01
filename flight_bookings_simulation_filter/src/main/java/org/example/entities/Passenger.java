package org.example.entities;

public class Passenger {
    private int id;
    private String name;
    private String lastname;
    private String documentNumber;

    public Passenger(int id, String name, String lastname, String documentNumber) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.documentNumber = documentNumber;
    }

    public Passenger(String name, String lastname, String documentNumber) {
        this.name = name;
        this.lastname = lastname;
        this.documentNumber = documentNumber;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public String toString() {
        var lines = new String[] {
                String.format("id: %d", getId()),
                String.format("name: %s", getName()),
                String.format("lastname: %s", getLastname()),
                String.format("documentNumber: %s", getDocumentNumber()),
        };
        return String.join("\n", lines);
    }
}
