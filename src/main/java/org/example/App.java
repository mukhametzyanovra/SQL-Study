package org.example;


import java.sql.*;

import java.util.Scanner;

import static java.sql.DriverManager.getConnection;
import static org.example.services.CommandService.*;

/**
 * Hello world!
 */
public class App {

    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "qwerty123";
    public static final String DB_URL = "jdbc:postgresql://localhost:5433/Box";

    public static void main(String[] args) throws SQLException {
        //Создали подключение
        Connection connection = getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Scanner sc = new Scanner(System.in);
        while (true) {
            printStartInfo();
            int command = sc.nextInt();
            if (command == 1) {
                executeCommand1(connection);
            } else if (command == 2) {
                executeCommand2(connection, sc);
            } else if (command == 3) {
                executeCommand3(connection, sc);
            } else if (command == 4) {
                System.exit(0);
            } else {
                System.err.println("Команда не распознанна");
            }
        }
    }
}

