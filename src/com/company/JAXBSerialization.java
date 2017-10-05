package com.company;

import java.io.File;
import java.sql.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBSerialization {
    public static void main(String[] args) {
        Student student = new Student((short) 1, "Alex","Alexeevich","Alexeev", Date.parse("1981/03/01"));

        try {

            File file = new File("Student.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(student, file);
            jaxbMarshaller.marshal(student, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        try {
            File file = new File("Student.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Student student1 = (Student) jaxbUnmarshaller.unmarshal(file);
            System.out.println(student1);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}