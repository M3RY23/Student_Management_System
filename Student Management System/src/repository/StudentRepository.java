package repository;

import entity.Student;
import exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void updateStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(student.getId())) {
                students.set(i, student);
                break;
            }
        }
    }

    public void deleteStudent(long id) {
        Student student = findById(id);
        students.removeIf(k -> k.getId().equals(id));
    }

    /* another solution
    public void removeStudent (Student student){
                for (Student s : students) {
                    if (s.getId() == student.getId()) {
                        students.remove(student);
                    }
                }
            }
            */

    public Student findById(long id) {
        return students.stream()
                .filter(k -> k.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Student with ID " + id + " not found!"));
    }

    public List<Student> findStudents() {
        return students;
    }
}
