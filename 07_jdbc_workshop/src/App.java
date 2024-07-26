import controllers.AuthorsController;
import controllers.BooksController;
import models.AuthorsModel;
import models.BooksModel;
import persistence.Database;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        System.out.println("***********************************************************");
        System.out.println("Authors and Books CRUD");
        System.out.println("***********************************************************");

        var database = new Database();
        var authorsModel = new AuthorsModel(database);
        var booksModel = new BooksModel(database);
        var authorsController = new AuthorsController(authorsModel);
        var booksController = new BooksController(booksModel);

        boolean isMenuOpened = true;
        while (isMenuOpened) {
            var menuOptionsMessage = """
                    ********************* Menu *********************
                    0. Exit.
                    1. Create author.
                    2. Show all authors.
                    3. Show author by id.
                    4. Update author.
                    5. Delete author.
                    6. Create book.
                    7. Show all books.
                    8. Show book by id.
                    9. Update book.
                    10. Delete book.
                    11. Show books by author id.
                    
                    Enter the option you can to run.
                    """;
            var option = JOptionPane.showInputDialog(menuOptionsMessage);
            if (option == null) return;

            switch (option) {
                case "0" -> isMenuOpened = false;
                case "1" -> authorsController.create();
                case "2" -> authorsController.findAll();
                case "3" -> authorsController.findById();
                case "4" -> authorsController.update();
                case "5" -> authorsController.delete();
                case "6" -> booksController.create();
                case "7" -> booksController.findAll();
                case "8" -> booksController.findById();
                case "9" -> booksController.update();
                case "10" -> booksController.delete();
                case "11" -> booksController.findAllByAuthorId();
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Try it again.");
            }
        }
    }
}
