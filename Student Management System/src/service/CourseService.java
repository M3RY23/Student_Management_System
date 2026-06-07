package service;

import entity.Course;

import java.util.List;

public interface CourseService {
    void addCourse(Course course);

    void deleteCourse(Long id);

   Course findByCourseId(Long id);

    List<Course> findAll();

}
