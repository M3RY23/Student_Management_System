package repository;

import entity.Course;
import exception.CourseNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private List<Course> courses =  new ArrayList<>();

    public void addCourse (Course course){
        courses.add(course);
    }

    public Course findByCourseId(long id) {
        return courses.stream()
                .filter(k -> k.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + id + " not found!"));
    }

    public void deleteCourse(long id) {
        findByCourseId(id);
        courses.removeIf(k -> k.getId().equals(id));
    }

    public List<Course> findCourses() {
        return courses;
    }
}
