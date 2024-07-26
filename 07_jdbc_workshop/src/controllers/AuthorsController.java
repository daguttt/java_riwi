package controllers;

import entities.Author;
import models.AuthorsModel;
import utils.Prompts;

import javax.swing.*;

public class AuthorsController {
    private final AuthorsModel authorsModel;
    public AuthorsController( AuthorsModel authorsModel) {
        this.authorsModel = authorsModel;
    }

    public void create() {
        var basePrompt = "Creating an author...";
        var namePrompt = Prompts.getPromptMessage(basePrompt, "Enter author's name:");
        var name = JOptionPane.showInputDialog(namePrompt);

        var nationalityPrompt = Prompts.getPromptMessage(basePrompt, "Enter author's nationality:");
        var nationality = JOptionPane.showInputDialog(nationalityPrompt);

        var baseAuthor = new Author(name, nationality);
        var createdAuthor = authorsModel.create(baseAuthor);

        var outputMessage = String.format("Author successfully created!%n%s", createdAuthor);
        JOptionPane.showMessageDialog(null, outputMessage);
    }

    public void findAll() {
        var authorList = authorsModel.findAll().stream().map(Author::toString).toList();

        if (authorList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No authors added yet.");
            return;
        }
        var authorListMessage = String.join("\n--------------\n",authorList);
        JOptionPane.showMessageDialog(null, authorListMessage);
    }

    public void findById() {
        var promptMessage = """
                Finding an author by id...
                
                Enter the author's id:
                """;
        var id = JOptionPane.showInputDialog(promptMessage);
        if (id.isBlank()) return;

        var foundAuthor = authorsModel.findById(Integer.parseInt(id));

        if (foundAuthor == null) {
            var notFoundMessage = String.format("Author with id '%s' not found", id);
            JOptionPane.showMessageDialog(null, notFoundMessage);
            return;
        }

        JOptionPane.showMessageDialog(null, foundAuthor);
    }

    public void update() {
        var promptMessage = """
                Updating an author...
                
                Enter the author's id:
                """;
        var id = JOptionPane.showInputDialog(promptMessage);
        if (id.isBlank()) return;

        var foundAuthor = authorsModel.findById(Integer.parseInt(id));

        if (foundAuthor == null) {
            var notFoundMessage = String.format("Author with id '%s' not found", id);
            JOptionPane.showMessageDialog(null, notFoundMessage);
            return;
        }

        var promptToUpdateName = Prompts.getPromptMessage(
                foundAuthor, "Enter the new author's name. (Press ENTER to skip)");
        var newName = JOptionPane.showInputDialog(promptToUpdateName);

        var promptToUpdateNationality = Prompts.getPromptMessage(
                foundAuthor, "Enter the new author's nationality. (Press ENTER to skip)");
        var newNationality = JOptionPane.showInputDialog(promptToUpdateNationality);

        foundAuthor.setName(newName.isBlank() ? foundAuthor.getName() : newName);
        foundAuthor.setNationality(newNationality.isBlank() ? foundAuthor.getNationality() : newNationality);

        System.out.printf("Updating author with id: %d", foundAuthor.getId());

        var couldUpdateAuthor = authorsModel.update(foundAuthor, foundAuthor.getId());

        if (couldUpdateAuthor)
            JOptionPane.showMessageDialog(null, "Author updated successfully!");
        else
            JOptionPane.showMessageDialog(null, "Couldn't update author!");
    }

    public void delete() {
        var promptMessage = """
                Deleting an author...
                
                Enter the author's id:
                """;
        var id = JOptionPane.showInputDialog(promptMessage);
        if (id.isBlank()) return;

        var couldDeleteAuthor = authorsModel.delete(Integer.parseInt(id));

        if (couldDeleteAuthor)
            JOptionPane.showMessageDialog(null, "Author deleted successfully!");
        else
            JOptionPane.showMessageDialog(null, "Author with id '%s' not found!");

    }
}
