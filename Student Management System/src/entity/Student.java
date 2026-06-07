package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends User {
    private String studentNumber;
    private List<Course> courses;
    private Map<Course, Integer> grades;

    public Student(Long id, String firstName, String lastName, String email, String studentNumber, String password) {
        super(id, firstName, lastName, email, password);
        this.studentNumber = studentNumber;
        this.courses = new ArrayList<>();
        this.grades = new HashMap<>();

    }

    // Everything for extra points :)
    public double calculateGPA() {
        if (grades.isEmpty()) return 0.0;
        double total = 0;

        for(Integer grade : grades.values()) {
            if(grade >= 90)
                total += 4.0;
            else if(grade >= 80)
                total += 3.0;
            else if(grade >= 70)
                total += 2.0;
            else if(grade >= 60)
                total += 1.0;
        }

        return total / grades.size();
    }

    public String getStudentNumber() {
        return studentNumber;
    }
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Map<Course, Integer> getGrades() {
        return grades;
    }
    public void setGrades(Map<Course, Integer> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", name='" + getFirstName() + " " + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                '}';
    }
}