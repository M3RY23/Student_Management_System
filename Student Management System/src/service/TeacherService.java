package service;

import entity.Teacher;
import java.util.List;

public interface TeacherService {
    void addTeacher(Teacher teacher);

    void deleteTeacher(Long id);

    void updateTeacher(Teacher teacher);

    Teacher findByTeacherId(Long id);

    List<Teacher> findAll();
}
