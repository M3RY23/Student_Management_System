package util;

import entity.Course;
import entity.Student;
import entity.Teacher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtil {
    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        try {
            File file = new File("students.txt");
            if (!file.exists()) return students;

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" , ");
                Student student = new Student(
                        Long.parseLong(parts[0]), // id
                        parts[1],                 // firstName
                        parts[2],                 // lastName
                        parts[3],                 // email
                        parts[4],                 // studentNumber
                        parts[5]                  // password
                );
                students.add(student);
            }
            fileScanner.close();
        } catch (Exception e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
        return students;
    }

    public static List<Teacher> loadTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        try {
            File file = new File("teachers.txt");
            if (!file.exists()) return teachers;

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" , ");
                Teacher teacher = new Teacher(Long.parseLong(parts[0]),// id
                        parts[1],                 // firstName
                        parts[2],                 // lastName
                        parts[3],                 // email
                        parts[4],                 // password
                        parts[5]                  // department
                );
                teachers.add(teacher);
            }
            fileScanner.close();
        } catch (Exception e) {
            System.err.println("Error loading teachers: " + e.getMessage());
        }
        return teachers;
    }

    public static List<Course> loadCourses(List<Teacher> teachers) {
        List<Course> courses = new ArrayList<>();
        try {
            File file = new File("courses.txt");
            if (!file.exists()) return courses;

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" , ");

                long teacherId = Long.parseLong(parts[2]);
                Teacher teacher = null;
                for (Teacher t : teachers) {
                    if (t.getId().equals(teacherId)) {
                        teacher = t;
                        break;
                    }
                }

                Course course = new Course(Long.parseLong(parts[0]), parts[1], teacher);
                courses.add(course);
            }
            fileScanner.close();
        } catch (Exception e) {
            System.err.println("Error loading courses: " + e.getMessage());
        }
        return courses;
    }

    public static void saveStudents(List<Student> students) {
        try {
            FileWriter writer = new FileWriter("students.txt");
            for (Student student : students) {
                writer.write(student.getId() + " , "
                        + student.getFirstName() + " , "
                        + student.getLastName() + " , "
                        + student.getEmail() + " , "
                        + student.getStudentNumber() + " , "
                        + student.getPassword() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }

    public static void saveTeachers(List<Teacher> teachers) {
        try {
            FileWriter writer = new FileWriter("teachers.txt");
            for (Teacher teacher : teachers) {
                writer.write(teacher.getId() + " , "
                        + teacher.getFirstName() + " , "
                        + teacher.getLastName() + " , "
                        + teacher.getEmail() + " , "
                        + teacher.getPassword() + " , "
                        + teacher.getDepartment() + "\n");
            }
            writer.close();
        } catch (
                IOException e) {
            System.err.println("Error saving teachers: " + e.getMessage());
        }
    }

    public static void saveCourses(List<Course> courses) {
        try {
            FileWriter writer = new FileWriter("courses.txt");
            for (Course course : courses) {
                writer.write(course.getId() + " , "
                        + course.getCourseName() + " , "
                        + course.getTeacher().getId()
                        + "\n");
            }
            writer.close();
        } catch (
                IOException e) {
            System.err.println("Error saving courses: " + e.getMessage());
        }
    }


}
