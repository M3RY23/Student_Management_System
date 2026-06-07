package service;

import entity.Student;
import java.util.List;

public interface StudentService {
   void addStudent(Student student);

    void deleteStudent(Long id);

    void updateStudent(Student student);

    Student findById(Long id);

    List<Student> findAll();
}
