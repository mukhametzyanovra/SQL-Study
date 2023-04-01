package org.example;


import java.sql.*;

import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

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
            System.out.println("1. Показать список всех задач");
            System.out.println("2. Выполнить задачу");
            System.out.println("3. Создать задачу");
            System.out.println("4. Выйти");
            int command = sc.nextInt();
            if (command == 1) {
                //объект - отправляет запросы в БД
                Statement statement = connection.createStatement();
                String SQL_SELECT_TASKS = "select * from parameters order by id desc";
                //хранит результат нашего запроса(позвоялет итерировать по строкам)
                ResultSet result = statement.executeQuery(SQL_SELECT_TASKS);
                // Просматриваем все данные, которые вернулись из бд
                //пока в резальтате что то есть
                while (result.next()) {
                    String firstName = result.getString("firstName");
                    String lastName = result.getString("lastName");
                    String patronymic = result.getString("patronymic");
                    Date birthDate = result.getDate("birthDate");
                    boolean sex = result.getBoolean("sex");

                    System.out.println(firstName + " "
                            + lastName + " "
                            + patronymic + " "
                            + birthDate + " "
                            + sex);
                }
            } else if (command == 2) {
                //описывем запрос незная какие парамет ры там будут
                String sql = "update parameters set lastname = 'petrov' where id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                System.out.println("Введите id");
                int keyId = sc.nextInt();
                //кладем параметр который мы считали с консоли
                preparedStatement.setInt(1, keyId);
                preparedStatement.executeUpdate();
            } else if (command == 3) {
                String sql = "insert into parameters (firstName, lastName, patronymic, birthDate, sex) values(?,'lastName', 'patronymic', '1991-05-05', false)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                System.out.println("Введите название задачи");
                String taskName = sc.next();
                preparedStatement.setString(1, taskName);
                preparedStatement.executeUpdate();
            } else if (command == 4) {
                System.exit(0);
            } else {
                System.err.println("Команда не распознанна");
            }
        }
    }
}

