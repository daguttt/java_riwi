package org.example;

import org.example.persistence.Database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        var host = args[0];
        var port = args[1];
        var dbName = args[2];
        var dbUser = args[3];
        var dbPassword = args[4];

        var database = new Database();
        try (var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Rlwl2023.")){
            System.out.println("DATABASE: Connection established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(database);
    }
}