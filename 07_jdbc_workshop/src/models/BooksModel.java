package models;

import entities.Book;
import persistence.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BooksModel implements Crud<Book> {
    private final Database database;

    public BooksModel(Database database) {
        this.database = database;
    }

    @Override
    public Book create(Book object) {
        var connection = database.openConnection();
        var sql = """
                INSERT INTO books (title, published_date, price, authors_id)\s
                    VALUES (?, ?, ?, ?);
                """;

        try {
            var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, object.getTitle());
            statement.setDate(2, object.getPublishedDate());
            statement.setFloat(3, object.getPrice());
            statement.setInt(4, object.getAuthorsId());

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
    public List<Book> findAll() {
        return List.of();
    }

    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public boolean update(Book object, int id) {
        return true;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
