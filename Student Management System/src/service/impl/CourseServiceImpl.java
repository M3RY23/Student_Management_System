package service.impl;

import entity.Course;
import repository.CourseRepository;
import service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository = new CourseRepository();


    @Override
    public void addCourse(Course course) {
        courseRepository.addCourse(course);

    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteCourse(id);
        System.out.println("Course deleted successfully");
    }

    @Override
    public Course findByCourseId(Long id) {
        return courseRepository.findByCourseId(id);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findCourses();
    }
}
