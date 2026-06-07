package service.impl;

import entity.Teacher;
import repository.TeacherRepository;
import service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository = new TeacherRepository();
    @Override
    public void addTeacher(Teacher teacher) {
        teacherRepository.addTeacher(teacher);

    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteTeacher(id);
        System.out.println("Teacher successfully deleted");
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherRepository.updateTeacher(teacher);
        System.out.println("Teacher successfully updated");
    }

    @Override
    public Teacher findByTeacherId(Long id) {
        return teacherRepository.findByTeacherId(id);
    }


    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findTeachers();
    }


}
