package repository;

import entity.Teacher;
import exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {
    private List<Teacher> teachers = new ArrayList<>();

    public void addTeacher(Teacher teacher) {
       teachers.add(teacher);
    }

    public void updateTeacher(Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(teacher.getId())) {
                teachers.set(i, teacher);
                break;
            }
        }
    }

    public void deleteTeacher(long id) {
        findByTeacherId(id);
        teachers.removeIf(k -> k.getId().equals(id));
    }
    /* Another solution
    public void removeTeacher (Teacher teacher){
                for (Teacher t : teachers) {
                    if (t.getId() == teacher.getId()) {
                        teachers.remove(teacher);
                    }
                }
            }
            */

    public Teacher findByTeacherId(long id) {

        return teachers.stream()
                .filter(k -> k.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Teacher with ID " + id + " not found!"));
    }

    public List<Teacher> findTeachers() {
        return teachers;
    }
}
