package controllers;

import entities.Book;
import models.AuthorsModel;
import models.BooksModel;
import utils.DateStringParser;
import utils.Prompts;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;

public class BooksController {
    private final BooksModel booksModel;
    private final AuthorsModel authorsModel;

    public BooksController(BooksModel booksModel, AuthorsModel authorsModel) {
        this.booksModel = booksModel;
        this.authorsModel = authorsModel;
    }

    public void create() {
        var basePrompt = "Creating an book...";
        var titlePrompt = Prompts.getPromptMessage(basePrompt, "Enter book's title:");
        var titleInput = JOptionPane.showInputDialog(titlePrompt);

        var publishedDatePrompt = Prompts.getPromptMessage(basePrompt, "Enter book's published date (Format: YYYY-MM-DD):");
        var publishedDateInput = askForLocalDate(publishedDatePrompt);

        var pricePrompt = Prompts.getPromptMessage(basePrompt, "Enter book's price:");
        var priceInput = JOptionPane.showInputDialog(pricePrompt);

        var authorIdPrompt = Prompts.getPromptMessage(basePrompt, "Enter book's author id:");
        var authorIdInput = JOptionPane.showInputDialog(authorIdPrompt);

        // Check author id existence
        var bookAuthor = authorsModel.findById(Integer.parseInt(authorIdInput));
        if (bookAuthor == null) {
            var notFoundAuthorMessage = String.format("Author with id '%s' not found", authorIdInput);
            JOptionPane.showMessageDialog(null, notFoundAuthorMessage);
            return;
        }

        var baseBook = new Book(
                titleInput,
                Date.valueOf(publishedDateInput),
                Float.parseFloat(priceInput),
                Integer.parseInt(authorIdInput)
        );
        var createdBook = booksModel.create(baseBook);

        var outputMessage = String.format("Book successfully created!%n%s", createdBook);
        JOptionPane.showMessageDialog(null, outputMessage);
    }

    public void findAll() {

    }
    public void findById() {}
    public void update() {}
    public void delete() {}
    public void findAllByAuthorId() {}

    private LocalDate askForLocalDate(String prompt) {
        while (true) {
            var dateStringInput = JOptionPane.showInputDialog(prompt);
            var parsingResult = DateStringParser.parse(dateStringInput);

            if (parsingResult.isPresent()) return parsingResult.get();

            JOptionPane.showMessageDialog(null, "Invalid date format. Try it again.");
        }
    }


}
