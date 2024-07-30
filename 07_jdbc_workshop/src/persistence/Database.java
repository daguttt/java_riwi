package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String dbUser;
    private final String dbPassword;

    public Database(String dbUser, String dbPassword) {
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    private Connection connection = null;

    public Connection openConnection() {
        var host = "magic-academy-magic-academy.g.aivencloud.com";
        var port = "22079";
        var databaseName = "jdbc_workshop";
        var url = String.format("jdbc:mysql://%s:%s/%s", host, port, databaseName);

        try {
            this.connection = DriverManager.getConnection(url, dbUser, dbPassword);
            System.out.println();
            System.out.println("Database connection established");
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException("ERROR: Couldn't connect to the database");
        }
        return this.connection;
    }

    public void closeConnection() {
        if (connection == null) return;
        try {
            if (connection.isClosed()) return;
            connection.close();
            System.out.println();
            System.out.println("Database connection closed");
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException("ERROR: A database error occurred");
        }
    }

    public void testConnection() {
        var connection = openConnection();
        try {
            var statement = connection.createStatement();
            statement.execute("SELECT 1 + 2 as result;");
            var resultSet = statement.getResultSet();
            resultSet.next();
            var queryResult = resultSet.getInt(1);
            System.out.println(queryResult);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closeConnection();
    }
}
