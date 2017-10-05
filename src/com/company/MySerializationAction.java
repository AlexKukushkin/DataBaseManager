package com.company;
import com.sun.org.apache.xpath.internal.Arg;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


public class MySerializationAction {
  static Group readGroup(String fileName) throws IOException,
                                      ClassNotFoundException {
    ObjectInputStream ois = new ObjectInputStream(
      new FileInputStream(fileName)
    );
    Group group = (Group) ois.readObject();
    return group;
  }

  static void serializeGroup(Group group) throws IOException {
    File file = new File("group.txt");
    // fos = new FileOutputStream(file);
    ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream(file)
    );
    oos.writeObject(group);
  }

  static Journal readJournalLesson(String fileName) throws IOException,
          ClassNotFoundException {
    ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream(fileName)
    );
    Journal journal = (Journal) ois.readObject();
    return journal;
  }
  static void serializeJournalLesson(Journal journal) throws IOException {
    File file = new File("journal_Lesson.txt");
    ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(file)
    );
    oos.writeObject(journal);
  }


  static Journal readJournalGroup(String fileName) throws IOException,
          ClassNotFoundException {
    ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream(fileName)
    );
    Journal journal = (Journal) ois.readObject();
    return journal;
  }
  static void serializeJournalGroup(Journal journal) throws IOException {
    File file = new File("journal_Group.txt");
    ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(file)
    );
    oos.writeObject(journal);
  }

  static Journal readJournalStudent(String fileName) throws IOException,
          ClassNotFoundException {
    ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream(fileName)
    );
    Journal journal = (Journal) ois.readObject();
    return journal;
  }
  static void serializeJournalStudent(Journal journal) throws IOException {
    File file = new File("journal_Student.txt");
    ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(file)
    );
    oos.writeObject(journal);
  }

  static void createStudentXML(Student student) throws ParserConfigurationException, TransformerException, IllegalAccessException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = dbf.newDocumentBuilder();
    DOMImplementation implementation = builder.getDOMImplementation();
    Document document = implementation.createDocument(null, null, null);
    Element elementStudent = document.createElement("object");
    elementStudent.setAttribute("type","Student");
    for (Field field:student.getClass().getDeclaredFields()){
      Element elementField = document.createElement("field");
      String fieldName = field.getName();
      field.setAccessible(true);
      String value = field.get(student).toString();
      elementField.setAttribute("type", field.getType().getName());
      elementField.setAttribute("id", fieldName);
      elementField.setAttribute("value", value);
      elementStudent.appendChild(elementField);
    }

    for (Method method : student.getClass().getDeclaredMethods()) {
      Element elementMethods = document.createElement("method");
      elementMethods.setAttribute("id", method.getName());
      elementMethods.setAttribute("return", method.getReturnType().getName());
      for (Parameter parameter : method.getParameters()) {
        Element paramElement = document.createElement("arg");
        paramElement.setAttribute("id", parameter.getName());
        paramElement.setAttribute("type", parameter.getType().getName());
        elementMethods.appendChild(paramElement);
      }
      elementStudent.appendChild(elementMethods);
    }


    document.appendChild(elementStudent);
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    Result output = new StreamResult(new File("Stud01.xml"));
    Source source = new DOMSource(document);
    transformer.transform(source, output);
  }
}
