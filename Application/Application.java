package Application;


import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to reset the Users database? (Y/N)");
        String input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            createDatabase();
            System.out.println("Database Reset.");
        }

        Menu menu = new Menu();
        try {
            menu.start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createDatabase() {
        String dropDatabase = "DROP DATABASE IF EXISTS users";
        String createDatabase = "CREATE DATABASE IF NOT EXISTS users";
        String useDatabase = "USE users";
        String createTable = "CREATE TABLE IF NOT EXISTS users (user_id INT PRIMARY KEY AUTO_INCREMENT, first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL);";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "password");
        } catch (SQLException e) {
            System.out.println("Couldn't find a connection.");
        }
        Statement statement = null;
        try {
            statement = connection.createStatement ();
        } catch (SQLException e) {
            System.out.println("Statement couldn't be created.");;
        }
        try {
            statement.executeUpdate(dropDatabase);
        } catch (SQLException e) {
            System.out.println("The drop database execute didn't work.");
        }
        try {
            statement.executeUpdate(createDatabase);
        } catch (SQLException e) {
            System.out.println("The create database execute didn't work.");
        }
        try {
            statement.executeUpdate(useDatabase);
        } catch (SQLException e) {
            System.out.println("The use database execute didn't work.");
        }
        try {
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            System.out.println("The create table execute didn't work.");
        }
    }
}
