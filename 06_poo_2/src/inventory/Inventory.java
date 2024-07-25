package inventory;

import utils.InputGetter;

import java.util.*;

public class Inventory {
    private final ArrayList<SpecificProduct> products;
    private int idToAssignToProducts = 1;

    public Inventory() {
        products = new ArrayList<>(
                Arrays.asList(
                        new SpecificProduct(idToAssignToProducts++, "Mouse", 199.99f, "Tech", "Logitech"),
                        new SpecificProduct(idToAssignToProducts++, "laptop", 1_399.99f, "Tech", "Apple")
                )
        );
    }

    public static void showMenu(Inventory inventory, Scanner scanner) {
        var inputGetter = new InputGetter(scanner);
        System.out.println("***********************************************************");
        System.out.println("Bienvenido a tu sistema de Inventario");
        System.out.println("***********************************************************");
        System.out.println();


        boolean isMenuOpened = true;
        while (isMenuOpened) {
            var optionsMessage = """
                    0. Salir.
                    1. Añadir producto.
                    2. Listar productos.
                    3. Eliminar producto por nombre.
                    4. Buscar producto por nombre o categoria.
                    """;
            System.out.println(optionsMessage);
            int option = inputGetter.get("Ingresa la opción que deseas hacer: ", Scanner::nextInt);


            switch (option) {
                case 0 -> isMenuOpened = false;
                case 1 -> {
                    scanner.nextLine();
                    var productName = inputGetter.get("Ingresa el nombre del producto: ", Scanner::nextLine).toLowerCase().trim();
                    var productPrice = inputGetter.get("Ingresa el precio del producto: ", Scanner::nextFloat);
                    scanner.nextLine();
                    var productCategory = inputGetter.get("Ingresa la categoria del producto: ", Scanner::nextLine).toLowerCase().trim();
                    var productBrand = inputGetter.get("Ingresa la marca del producto: ", Scanner::nextLine).toLowerCase().trim();
                    inventory.addProduct(productName, productPrice, productCategory, productBrand);
                    System.out.println();

                    System.out.println("¡Producto añadido satisfactoriamente!");
                    System.out.println();
                }
                case 2 -> {
                    scanner.nextLine();
                    var products = inventory.getProducts();
                    if (products.isEmpty()) {
                        System.out.println("No hay productos en el inventario actualmente.");
                        break;
                    }

                    // Table Header
                    var tableHeader = String.format("| %-15s | %-15s |", "Nombre", "Precio");
                    System.out.println(tableHeader);
                    System.out.println("-".repeat(tableHeader.length()));
                    // Table Body
                    for (var product : products) {
                        var productName = Character.toUpperCase(product.getName().charAt(0)) + product.getName().substring(1).toLowerCase();
                        System.out.printf("| %-15s | %-15.2f |%n", productName, product.getPrice());
                    }
                    System.out.println();
                }
                case 3 -> {
                    scanner.nextLine();
                    var productNameToDelete = inputGetter.get("Introduce el nombre del producto que quieres eliminar: ", Scanner::nextLine);
                    System.out.println();
                    try {
                        inventory.removeProductByName(productNameToDelete.trim().toLowerCase());
                        var capitalizedProductNameToDelete = Character.toUpperCase(productNameToDelete.charAt(0)) + productNameToDelete.substring(1).toLowerCase();
                        System.out.printf("Producto '%s' eliminado con éxito.%n", capitalizedProductNameToDelete);
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                        System.out.println();
                    }
                }
                case 4 -> {
                    scanner.nextLine();
                    var query = inputGetter.get("Introduce el nombre o la categoria del producto a buscar: ", Scanner::nextLine);
                    System.out.println();
                    var searchResult = inventory.findProductByCategoryOrName(query);
                    if (searchResult.isPresent()) {
                        var product = searchResult.get();
                        System.out.printf(
                                "ID: %s%n" +
                                        "Nombre: %s%n" +
                                        "Precio: %.2f%n" +
                                        "Categoria: %s%n" +
                                        "Brand: %s%n", product.getId(), product.getCapitalizedName(), product.getPrice(), product.getCategory(), product.getBrand());
                        System.out.println();
                    } else {
                        System.out.printf("No se encontró ningún producto con la query: '%s'%n", query);
                        System.out.println();
                    }


                }
                default -> {
                    System.out.println("Opción seleccionada inválida. Inténtalo de nuevo.");
                    System.out.println();
                }
            }
        }
    }

    public void addProduct(String name, float price, String category, String brand) {
        var product = new SpecificProduct(idToAssignToProducts++, name, price, category, brand);
        this.products.add(product);
    }

    public void removeProductByName(String productNameToRemove) throws NoSuchElementException {
        SpecificProduct productToRemove = null;
        for (var product : this.products) {
            if (product.getName().equals(productNameToRemove)) productToRemove = product;
        }

        if (productToRemove == null)
            throw new NoSuchElementException("Elemento con nombre " + productNameToRemove + "no encontrado");

        this.products.remove(productToRemove);
    }

    public Optional<SpecificProduct> findProductByCategoryOrName(String query) {
        var sanitizedQuery = query.trim().toLowerCase();
        SpecificProduct foundProduct = null;
        for (var product : this.products) {
            if (product.getName().equals(sanitizedQuery) || product.getCategory().equals(sanitizedQuery))
                foundProduct = product;
        }

        return Optional.ofNullable(foundProduct);

    }

    // Getter / Setters

    public List<SpecificProduct> getProducts() {
        return this.products.stream().toList();
    }

}
