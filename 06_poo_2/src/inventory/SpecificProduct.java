package inventory;

public class SpecificProduct extends Product {
    private String category;
    private String brand;

    public SpecificProduct() {
    }

    public SpecificProduct(int id, String name, float price) {
        super(id, name, price);
    }

    public SpecificProduct(int id, String name, float price, String category, String brand) {
        super(id, name, price);
        this.category = category;
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
