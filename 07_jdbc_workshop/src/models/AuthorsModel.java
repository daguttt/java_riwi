package models;

import entities.Author;
import persistence.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorsModel implements Crud<Author> {

    private final Database database;

    public AuthorsModel(Database database) {
        this.database = database;
    }

    @Override
    public Author create(Author object) {
        var connection = database.openConnection();
        var sql = """
                INSERT INTO authors (name, nationality)\s
                    VALUES (?, ?);
                """;

        try {
            var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, object.getName());
            statement.setString(2, object.getNationality());

            statement.execute();

            var resultSet = statement.getGeneratedKeys();
            resultSet.next();

            var givenAuthorId = resultSet.getInt(1);
            object.setId(givenAuthorId);
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return object;
    }

    @Override
    public List<Author> findAll() {
        var connection = database.openConnection();
        var sql = "SELECT * FROM authors;";

        var authorList = new ArrayList<Author>();

        try {
            var statement = connection.createStatement();

            statement.execute(sql);

            var resultSet = statement.getResultSet();

            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var name = resultSet.getString("name");
                var nationality = resultSet.getString("nationality");
                var author = new Author(id, name, nationality);
                authorList.add(author);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return authorList.stream().toList();
    }

    @Override
    public Author findById(int authorIdToFind) {
        var connection = database.openConnection();
        var sql = "SELECT id, name, nationality FROM authors WHERE id = ?";

        Author author;
        try(var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, authorIdToFind);

            var resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;

            var id = resultSet.getInt("id");
            var name = resultSet.getString("name");
            var nationality = resultSet.getString("nationality");
            author = new Author(id, name, nationality);

            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return author;
    }

    @Override
    public boolean update(Author object, int id) {
        var connection = database.openConnection();
        var sql = """
                UPDATE LOW_PRIORITY authors\s
                SET
                    name = ?,
                    nationality = ?
                WHERE id = ?;
               """;

        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, object.getName());
            statement.setString(2, object.getNationality());
            statement.setInt(3, id);

            var affectedRows = statement.executeUpdate();
            statement.close();

            if (affectedRows == 1) return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return false;
    }

    @Override
    public boolean delete(int id) {
        var connection = database.openConnection();
        var sql = """
                DELETE FROM authors\s
                WHERE id = ?;
               """;

        try (var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            var affectedRows = statement.executeUpdate();
            statement.close();

            if (affectedRows == 1) return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return false;
    }

}
