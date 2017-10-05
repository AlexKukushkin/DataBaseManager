package com.company;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String... args) throws Exception {
        Set<Student> presentStudents;
        Set<Student> allStudents = new HashSet<>();

        Lesson lesson01 = new Lesson(Date.parse("2017/09/21"),"Параметризованные типы", (short) 1,"Артем");

        Group group01 = new Group(1,"Группа доп. подготовки");
        Group group02 = new Group(2, "Группа 2");


        Student s01 = new Student((short) 1, "Иван","Иванович","Иванов", Date.parse("1981/03/01"));
        Student s02 = new Student((short) 2, "Петр","Иванович","Иванов", Date.parse("1981/03/01"));
        Student s03 = new Student((short) 3, "Сергей","Иванович","Иванов", Date.parse("1981/03/01"));
        Student s04 = new Student((short) 4, "Михаил","Иванович","Иванов", Date.parse("1981/03/01"));
        Student s05 = new Student((short) 5, "Андрей","Иванович","Иванов", Date.parse("1981/03/01"));
        Student s06 = new Student((short) 6, "Владислав","Иванович","Иванов", Date.parse("1981/03/01"));
        Student s07 = new Student((short) 7, "Олег","Иванович","Иванов", Date.parse("1981/03/01"));
        group01.addStudent(s01);
        group01.addStudent(s02);
        group01.addStudent(s03);
        presentStudents = new HashSet<>(group01.getStudents());
        allStudents = group01.getStudents();

        allStudents.add(s04);
        allStudents.add(s05);
        allStudents.add(s06);
        allStudents.add(s07);
        Journal journal01 = new Journal(lesson01, presentStudents);
        Set <Student> absentStudents = new HashSet<>(allStudents);
        absentStudents.removeAll(presentStudents);

        try {
            MySerializationAction.serializeGroup(group01);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Group restoredGroup =
                    MySerializationAction.readGroup("group.txt");
        } catch (IOException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            MySerializationAction.serializeJournalLesson(journal01);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Journal journalLesson =
                    MySerializationAction.readJournalLesson("journal_Lesson.txt");
            String date = new Date(journalLesson.getLesson().getDateTime()).toString();

            System.out.println("------------ Lesson ------------\n" + "date : " + date + "\ntheme : " + journalLesson.getLesson().getTopic());
            System.out.println("--- Students (present) ---\n" + journalLesson.getPresentSet());
            System.out.println("--- Students (absent) ---\n" + absentStudents);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            MySerializationAction.serializeJournalGroup(journal01);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Journal journalGroup =
                    MySerializationAction.readJournalGroup("journal_Group.txt");
            Set <Group> groups = new HashSet<>();
            System.out.println("------------ Group ------------\n" + journalGroup.getGroups());
            System.out.println("--- Students (present) ---\n" + journalGroup.getPresentSet());
            System.out.println("--- Students (absent) ---\n" + absentStudents);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            MySerializationAction.serializeJournalStudent(journal01);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Journal journalStudent =
                    MySerializationAction.readJournalStudent("journal_Group.txt");
            Set <Group> groups = new HashSet<>();
            System.out.println("------------ Students ------------\n" + journalStudent.getLesson().getTopic());
            System.out.println("--- Students (present) ---\n" + journalStudent.getPresentSet());
            System.out.println("--- Students (absent) ---\n" + absentStudents);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        MySerializationAction.createStudentXML(s01);
    }
}
