package service.impl;

import entity.Student;
import repository.StudentRepository;
import service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository = new StudentRepository();

    @Override
    public void addStudent(Student student) {
        studentRepository.addStudent(student);

    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteStudent(id);
        System.out.println("Student successfully deleted");

    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.updateStudent(student);
        System.out.println("Student successfully updated");
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findStudents();
    }
}
