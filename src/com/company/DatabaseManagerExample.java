package com.company;

import java.sql.*;

public class DatabaseManagerExample {
    public static void main(String[] args)
            throws ClassNotFoundException {
        saveStudent(
                new Student((short) 2, "Сара", "Луиза", "Коннор",
                        Date.parse("1981/03/01")));
        showStudents();
//
//        saveLesson(
//                new Lesson(Date.parse("1991/07/07"), "Сетевые протоколы", 255, "Виталий Михайлович Иванов"));
//
//        showLesson();
        deleteLesson(new Lesson(Date.parse("1991/07/07"), "Сетевые протоколы", 255, "Виталий Михайлович Иванов"));
        showLesson();
    }

    public static void showStudents()
            throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        Connection connection;
        try {
            connection =
                    DriverManager
                            .getConnection(
                                    "jdbc:postgresql://localhost:5432/students",
                                    "postgres",
                                    "admin");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM student");
            while (resultSet.next()) {
                Student student = new Student(
                        (short) resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("second_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("birth_date").toLocalDate().toEpochDay());
                System.out.println(student.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveStudent(Student student)
            throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        Connection connection;
        try {
            connection =
                    DriverManager
                            .getConnection(
                                    "jdbc:postgresql://localhost:5432/students",
                                    "postgres",
                                    "admin");
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO student(first_name, second_name, last_name, birth_date, group_id) VALUES(?, ?, ?, ?, ?)");
            statement.setString(1,
                    student.getFirstName());
            statement.setString(2,
                    student.getSecondName());
            statement.setString(3,
                    student.getFamilyName());
            statement.setDate(4,
                    new Date(student.getBirthDay()));
            statement.setInt(5, 1);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //=====================================

    public static void showLesson()
            throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        Connection connection;
        try {
            connection =
                    DriverManager
                            .getConnection(
                                    "jdbc:postgresql://localhost:5432/students",
                                    "postgres",
                                    "admin");
            Statement statementJournal = connection.createStatement();
            ResultSet resultSet = statementJournal
                    .executeQuery("SELECT * FROM lesson");
            while (resultSet.next()) {
                Lesson lesson = new Lesson(
                        resultSet.getDate("lesson_date").toLocalDate().toEpochDay(),
                        resultSet.getString("topic"),
                        resultSet.getShort("room_number"),
                        resultSet.getString("teacher_name"));
                System.out.println(lesson.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveLesson(Lesson lesson)
            throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        Connection connection;
        try {
            connection =
                    DriverManager
                            .getConnection(
                                    "jdbc:postgresql://localhost:5432/students",
                                    "postgres",
                                    "admin");
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO lesson(topic, lesson_date, room_number, teacher_name) VALUES(?, ?, ?, ?)");
            //statement.setInt(1, 11);
            statement.setString(1,
                    lesson.getTopic());
            statement.setDate(2,
                    new Date(lesson.getDateTime()));
            statement.setInt(3,
                    lesson.getRoomNum());
            statement.setString(4,
                    lesson.getTeacher());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteLesson(Lesson lesson)
            throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        Connection connection;
        try {
            connection =
                    DriverManager
                            .getConnection(
                                    "jdbc:postgresql://localhost:5432/students",
                                    "postgres",
                                    "admin");
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM lesson WHERE topic = 'Многопоточность Java'");

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}