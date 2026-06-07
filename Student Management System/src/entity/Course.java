package entity;

public class Course {
    private Long id;
    private String courseName;
    private Teacher teacher;


    public Course(Long id, String courseName, Teacher teacher) {
        this.id = id;
        this.courseName = courseName;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", teacher=" + (teacher != null ? teacher.getFirstName() + " " + teacher.getLastName() : "No teacher") +
                '}';
    }
}
