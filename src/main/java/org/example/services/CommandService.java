package org.example.services;

import java.sql.*;
import java.util.Scanner;

public class CommandService {
    /**
     * печатает функционал кнопок для выбора в коммандной строке
     */
    public static void printStartInfo(){
        System.out.println("1. Показать список всех задач");
        System.out.println("2. Выполнить задачу");
        System.out.println("3. Создать задачу");
        System.out.println("4. Выйти");
    }

    /**
     * метод выполняет функционал при выборе кнопки 1
     * получение всех объектов из таблицы
     * @param connection
     */
    public static void executeCommand1(Connection connection) {
        //объект - отправляет запросы в БД

        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            String SQL_SELECT_TASKS = "select * from parameters order by id desc";
            //хранит результат нашего запроса(позвоялет итерировать по строкам)

            result = statement.executeQuery(SQL_SELECT_TASKS);

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * метод выполняет функционал при выборе кнопки 2
     * обновление в таблице фамилии по айди
     * @param connection
     * @param sc
     */
    public static void executeCommand2(Connection connection, Scanner sc) {
        try {
            //описывем запрос незная какие парамет ры там будут
            String sql = "update parameters set lastname = 'petrov' where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println("Введите id");
            int keyId = sc.nextInt();
            //кладем параметр который мы считали с консоли
            preparedStatement.setInt(1, keyId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    /**
     * метод выполняет функционал при выборе кнопки 3
     * вставляет в таблицу запись
     * @param connection
     * @param sc
     */
    public static void executeCommand3(Connection connection, Scanner sc) {
        try{
            String sql = "insert into parameters (firstName, lastName, patronymic, birthDate, sex) values(?,'lastName', 'patronymic', '1991-05-05', false)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println("Введите название задачи");
            String taskName = sc.next();
            preparedStatement.setString(1, taskName);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
