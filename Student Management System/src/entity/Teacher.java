package entity;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {
    private String department;
    private List<Course> courses;

    public Teacher(Long id, String firstName, String lastName, String email, String password , String department) {
        super(id, firstName, lastName, email, password);
        this.department = department;
        this.courses = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + getId() +
                ", name='" + getFirstName() + " " + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
