package entities;

public class Author {
    private Integer id = null;
    private String name;
    private String nationality;

    public Author(int id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }

    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        var lines = new String[] {
                String.format("id: %d", this.getId()),
                String.format("name: %s", this.getName()),
                String.format("nationality: %s", this.getNationality()),
        };
        return String.join("\n", lines);
    }
}
