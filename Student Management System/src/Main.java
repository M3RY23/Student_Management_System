import entity.Course;
import entity.Student;
import entity.Teacher;
import exception.CourseNotFoundException;
import exception.UserNotFoundException;
import service.impl.CourseServiceImpl;
import service.impl.StudentServiceImpl;
import service.impl.TeacherServiceImpl;
import util.FileUtil;
import util.GPAUtil;
import util.ValidationUtil;

import java.io.File;
import java.util.*;


public class Main {
    static Scanner input = new Scanner(System.in);
    static StudentServiceImpl studentService = new StudentServiceImpl();
    static TeacherServiceImpl teacherService = new TeacherServiceImpl();
    static CourseServiceImpl courseService = new CourseServiceImpl();

    public static void main(String[] args) {
        File file = new File("students.txt");
        if (file.exists()) {
            List<Teacher> teachers = FileUtil.loadTeachers();
            List<Course> courses = FileUtil.loadCourses(teachers);
            List<Student> students = FileUtil.loadStudents();

            for (Teacher t : teachers) teacherService.addTeacher(t);
            for (Course c : courses) courseService.addCourse(c);
            for (Student s : students) studentService.addStudent(s);
        } else {
            loadSampleData();
        }

        while (true) {
            System.out.println("1 - Login");
            System.out.println("2 - Exit");
            System.out.println("Enter your choice");
            int choice = input.nextInt();

            if (choice == 1) {
                login();
            } else if (choice == 2) {
                FileUtil.saveStudents(studentService.findAll());
                FileUtil.saveTeachers(teacherService.findAll());
                FileUtil.saveCourses(courseService.findAll());
                System.out.println("GOODBYE");
                break;
            }
        }


    }

    static void login() {
        System.out.println("Enter your email: ");
        String email = input.next();
        System.out.println("Enter your password: ");
        String password = input.next();

        if (email.equals("admin@gmail.com") && password.equals("12345")) {
            System.out.println("Welcome Boss");
            adminMenu();
        } else {
            for (Teacher teacher : teacherService.findAll()) {
                if (teacher.getEmail().equals(email) && teacher.getPassword().equals(password)) {
                    System.out.println("Welcome " + teacher.getFirstName());
                    teacherMenu(teacher);
                    return;
                }
            }
            for (Student student : studentService.findAll()) {
                if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                    System.out.println("Welcome my dear " + student.getFirstName());
                    studentMenu(student);
                    return;
                }
            }
            System.out.println("Invalid credentials!");
        }


    }

    //Adding contacts
    static void loadSampleData() {
        // Teachers
        Teacher t1 = new Teacher(1234L, "Adam", "Barry", "Adam@gmail.com", "1234", "Java");
        Teacher t2 = new Teacher(2345L, "Kuzzat", "Altay", "Kuzzat@gmail.com", "1234", "Computer Science");
        Teacher t3 = new Teacher(3456L, "Albert", "Einstein", "Albert@gmail.com", "1234", "Math And Science");
        Teacher t4 = new Teacher(4567L, "Micheal", "Jackson", "Micheal@gmail.com", "1234", "Music");

        List<Teacher> teachers = Arrays.asList(t1, t2, t3, t4);
        for (Teacher teacher : teachers) {
            teacherService.addTeacher(teacher);
        }


        // Courses
        Course c1 = new Course(1243L, "Java Programming", t1);
        Course c2 = new Course(2443L, "Computer Science", t2);
        Course c3 = new Course(9743L, "Mathematics", t3);
        Course c4 = new Course(7453L, "Music", t4);
        Course c5 = new Course(9645L, "Science", t3);
        List<Course> courses = Arrays.asList(c1, c2, c3, c4, c5);
        for (Course course : courses) {
            courseService.addCourse(course);
        }


        // Students
        Student s1 = new Student(1854L, "Meryem", "Ihsan", "Meryem@gmail.com", "ST1001", "1234");
        Student s2 = new Student(1532L, "Alice", "Brown", "alice@gmail.com", "ST1002", "1234");
        Student s3 = new Student(5342L, "Muhammed", "Grey", "Muhammed@gmail.com", "ST1003", "1234");
        Student s4 = new Student(9775L, "Tomris", "Hatun", "Tomris@gmail.com", "ST1004", "1234");
        Student s5 = new Student(9567L, "Diana", "Queen", "Queen@gmail.com", "ST1005", "1234");
        Student s6 = new Student(6365L, "John", "Smith", "John@gmail.com", "ST1006", "1234");
        Student s7 = new Student(5732L, "Leonardo", "DaVinci", "Leonardo@gmail.com", "ST1007", "1234");
        Student s8 = new Student(4662L, "Nikola", "Tesla", "Nikola@gmail.com", "ST1008", "1234");
        Student s9 = new Student(2431L, "Charles", "Darwin", "Charles@gmail.com", "ST1009", "1234");
        Student s10 = new Student(9763L, "Isaac", "Newton", "Isaac@gmail.com", "ST1010", "1234");
        List<Student> students = Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10);
        for (Student student : students) {
            studentService.addStudent(student);
        }
    }


    //STUDENT
    static void studentMenu(Student student) {
        while (true) {
            System.out.println("1 - View Profile");
            System.out.println("2 - View Courses");
            System.out.println("3 - View My Grades");
            System.out.println("4 - View GPA");
            System.out.println("5 - Logout");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    viewProfile(student);
                    break;
                case 2:
                    viewCourses(student);
                    break;
                case 3:
                    viewGrades(student);
                    break;
                case 4:
                    viewGPA(student);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    static void viewGPA(Student student) {
        if (student.getGrades().isEmpty()) {
            System.out.println("There is no grades for this student!");
            return;
        }
        System.out.println("GPA: " + GPAUtil.calculateGPA(student));
    }

    static void viewGrades(Student student) {
        if (student.getGrades().isEmpty()) {
            System.out.println("There is no grades for this student!");
            return;
        }
        for (Map.Entry<Course, Integer> entry : student.getGrades().entrySet()) {
            System.out.println("  " + entry.getKey().getCourseName() + " - " + entry.getValue());
        }
    }

    static void viewCourses(Student student) {
        if (student.getCourses().isEmpty()) {
            System.out.println("There are no courses for this student!");
            return;
        }
        for (Course course : student.getCourses()) {
            System.out.println(course);
        }
    }

    static void viewProfile(Student student) {
        System.out.println("==== MY PROFILE ====");
        System.out.println("First Name: " + student.getFirstName());
        System.out.println("Last Name: " + student.getLastName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Student Number = " + student.getStudentNumber());


    }


    //TEACHER
    static void teacherMenu(Teacher teacher) {
        while (true) {
            System.out.println("1 - View Courses");
            System.out.println("2 - View Students");
            System.out.println("3 - Assign Grade");
            System.out.println("4 - Update Grade");
            System.out.println("5 - Course Average");
            System.out.println("6 - Logout");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    viewCoursesTeacher(teacher);
                    break;
                case 2:
                    viewStudent(teacher);
                    break;
                case 3:
                    assingGrade(teacher);
                    break;
                case 4:
                    updateGrade(teacher);
                    break;
                case 5:
                    courseAverage(teacher);
                    break;
                case 6: {
                    System.out.println("Goodbye!");
                    return;
                }
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    static void courseAverage(Teacher teacher) {
        if (teacher.getCourses().isEmpty()) {
            System.out.println("No courses found!");
            return;
        }
        for (Course course : teacher.getCourses()) {
            double total = 0;
            int count = 0;
            for (Student student : studentService.findAll()) {
                if (student.getGrades().containsKey(course)) {
                    total += student.getGrades().get(course);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println(course.getCourseName() + " - No grades yet.");
            } else {
                System.out.println(course.getCourseName() + " - Average: " + (total / count));
            }
        }
    }

    static void viewStudent(Teacher teacher) {
        for (Course course : teacher.getCourses()) {
            System.out.println("course: " + course.getCourseName());
            for (Student student : studentService.findAll()) {
                if (student.getCourses().contains(course)) {
                    System.out.println(" " + student);
                }
            }
        }
    }

    static void updateGrade(Teacher teacher) {
        System.out.println("Enter Student ID: ");
        long studentID = input.nextLong();
        try {
            Student student = studentService.findById(studentID);
            System.out.println("Enter Course ID: ");
            long courseID = input.nextLong();
            Course course = courseService.findByCourseId(courseID);
            if (!teacher.getCourses().contains(course)) {
                System.out.println("This is not your course!");
                return;
            }
            if (!student.getGrades().containsKey(course)) {
                System.out.println("No grade found for this course!");
                return;
            }
            System.out.println("Enter New Grade: ");
            int grade = input.nextInt();
            student.getGrades().put(course, grade);
            System.out.println("Grade updated successfully!");
        } catch (UserNotFoundException | CourseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static void assingGrade(Teacher teacher) {
        System.out.println("Enter Student ID: ");
        long studentID = input.nextLong();
        try {
            Student student = studentService.findById(studentID);
            System.out.println("Enter Course ID: ");
            long courseID = input.nextLong();
            Course course = courseService.findByCourseId(courseID);
            if (!teacher.getCourses().contains(course)) {
                System.out.println("This is not your course!");
                return;
            }
            System.out.println("Enter Grade: ");
            int grade = input.nextInt();
            student.getGrades().put(course, grade);
            System.out.println("Grade assigned successfully!");
        } catch (UserNotFoundException | CourseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static void viewCoursesTeacher(Teacher teacher) {
        if (teacher.getCourses().isEmpty()) {
            System.out.println("There are no courses for this teacher!");
            return;
        }
        for (Course course : teacher.getCourses()) {
            System.out.println(course);
        }
    }


    //ADMIN
    static void adminMenu() {
        while (true) {
            System.out.println("Administrator");
            System.out.println("1 - Add  Student");
            System.out.println("2 - Update  Student");
            System.out.println("3 - Delete  Student");
            System.out.println("4 - List  Students");
            System.out.println("5 - Add Teacher");
            System.out.println("6 - Update  Teacher");
            System.out.println("7 - Delete  Teacher");
            System.out.println("8 - List  Teachers");
            System.out.println("9 - Add Course");
            System.out.println("10 - Delete  Course");
            System.out.println("11 - List  Courses");
            System.out.println("12 - View All Grades");
            System.out.println("13 - Reports");
            System.out.println("14 - Search Student");
            System.out.println("15 - Logout");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    listStudent();
                    break;
                case 5:
                    addTeacher();
                    break;
                case 6:
                    updateTeacher();
                    break;
                case 7:
                    deleteTeacher();
                    break;
                case 8:
                    listTeacher();
                    break;
                case 9:
                    addCourse();
                    break;
                case 10:
                    deleteCourse();
                    break;
                case 11:
                    listCourse();
                    break;
                case 12:
                    viewAllGrades();
                    break;
                case 13:
                    reports();
                    break;
                case 14:
                    searchStudent();
                    break;
                case 15: {
                    System.out.println("LOGGED OUT");
                    return;
                }
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void addStudent() {
        System.out.println("Enter Your Student ID: ");
        long studentID = input.nextLong();
        if (!ValidationUtil.isValidId(studentID)) {
            System.out.println("Invalid ID!");
            return;
        }

        System.out.println("Enter Your First Name: ");
        String studentFirstName = input.next();
        if (!ValidationUtil.isValidName(studentFirstName)) {
            System.out.println("Invalid name!");
            return;
        }

        System.out.println("Enter Your Last Name: ");
        String studentLastName = input.next();
        if (!ValidationUtil.isValidName(studentLastName)) {
            System.out.println("Invalid name!");
            return;
        }

        System.out.println("Enter Your Student Email: ");
        String studentEmail = input.next();
        if (!ValidationUtil.isValidEmail(studentEmail)) {
            System.out.println("Invalid email!");
            return;
        }

        System.out.println("Enter Your Student Number: ");
        String studentNumber = input.next();
        if (!ValidationUtil.isValidStudentNumber(studentNumber)) {
            System.out.println("Invalid student number! Example: ST1001");
            return;
        }

        System.out.println("Enter Your Student Password: ");
        String studentPassword = input.next();
        if (!ValidationUtil.isValidPassword(studentPassword)) {
            System.out.println("Password must be at least 4 characters!");
            return;
        }

        Student student = new Student
                (studentID,
                        studentFirstName,
                        studentLastName,
                        studentEmail,
                        studentNumber,
                        studentPassword);
        studentService.addStudent(student);
        System.out.println("Student successfully added");
    }

    static void updateStudent() {
        System.out.println("Enter Your Student ID");
        long studentID = input.nextLong();
        try {
            studentService.findById(studentID);
            System.out.println("Enter Your New First Name: ");
            String studentNewFirstName = input.next();
            System.out.println("Enter Your New Last Name: ");
            String studentNewLastName = input.next();
            System.out.println("Enter Your New Student Email: ");
            String studentNewEmail = input.next();
            System.out.println("Enter Your New Student Number: ");
            String studentNewNumber = input.next();
            System.out.println("Enter Your New Student Password: ");
            String studentNewPassword = input.next();

            Student update = new Student(studentID,
                    studentNewFirstName,
                    studentNewLastName,
                    studentNewEmail,
                    studentNewNumber,
                    studentNewPassword);
            studentService.updateStudent(update);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    static void deleteStudent() {
        System.out.println("Enter Your Student ID");
        long studentID = input.nextLong();
        try {
            studentService.deleteStudent(studentID);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static void listStudent() {
        for (Student student : studentService.findAll()) {
            System.out.println(student);
        }
    }

    static void addTeacher() {
        System.out.println("Enter Your Teacher ID: ");
        long teacherID = input.nextLong();
        if (!ValidationUtil.isValidId(teacherID)) {
            System.out.println("Invalid ID!");
            return;
        }

        System.out.println("Enter Your First Name: ");
        String teacherfirstName = input.next();
        if (!ValidationUtil.isValidName(teacherfirstName)) {
            System.out.println("Invalid name!");
            return;
        }

        System.out.println("Enter Your Last Name: ");
        String teacherlastName = input.next();
        if (!ValidationUtil.isValidName(teacherlastName)) {
            System.out.println("Invalid name!");
            return;
        }

        System.out.println("Enter Your Teacher Email: ");
        String teacherEmail = input.next();
        if (!ValidationUtil.isValidEmail(teacherEmail)) {
            System.out.println("Invalid email!");
            return;
        }

        System.out.println("Enter Your Teacher Password: ");
        String teacherPassword = input.next();
        if (!ValidationUtil.isValidPassword(teacherPassword)) {
            System.out.println("Password must be at least 4 characters!");
            return;
        }

        System.out.println("Enter Your Department: ");
        String teacherDepartment = input.next();
        Teacher teacher = new Teacher
        (teacherID,
                teacherfirstName,
                teacherlastName,
                teacherEmail,
                teacherPassword,
                teacherDepartment);
        teacherService.addTeacher(teacher);
        System.out.println("Teacher successfully added");
    }

    static void updateTeacher() {
        System.out.println("Enter Your Teacher ID");
        long teacherID = input.nextLong();
        try {
            teacherService.findByTeacherId(teacherID);
            System.out.println("Enter Your New First Name: ");
            String teacherNewFirstName = input.next();
            System.out.println("Enter Your New Last Name: ");
            String teacherNewLastName = input.next();
            System.out.println("Enter Your New Teacher Email: ");
            String teacherNewEmail = input.next();
            System.out.println("Enter Your New Teacher Password: ");
            String teacherNewPassword = input.next();
            System.out.println("Enter Your New Department: ");
            String teacherNewDepartment = input.next();

            Teacher update = new Teacher
                    (teacherID,
                            teacherNewFirstName,
                            teacherNewLastName,
                            teacherNewEmail,
                            teacherNewPassword,
                            teacherNewDepartment);
            teacherService.updateTeacher(update);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    static void deleteTeacher() {
        System.out.println("Enter Your Teacher ID");
        long teacherID = input.nextLong();
        try {
            teacherService.deleteTeacher(teacherID);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static void listTeacher() {
        for (Teacher teacher : teacherService.findAll()) {
            System.out.println(teacher);
        }
    }

    static void addCourse() {
        System.out.println("Enter Your Course ID: ");
        long courseID = input.nextLong();
        System.out.println("Enter Your Course Name: ");
        String courseName = input.nextLine();
        System.out.println("Enter Teacher ID: ");
        long teacherID = input.nextLong();
        try {
            Teacher teacher = teacherService.findByTeacherId(teacherID);
            Course course = new Course(courseID, courseName, teacher);
            courseService.addCourse(course);
            System.out.println("Course added successfully");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static void deleteCourse() {
        System.out.println("Enter Your Course ID");
        long courseID = input.nextLong();
        try {
            courseService.deleteCourse(courseID);
        } catch (CourseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static void listCourse() {
        for (Course course : courseService.findAll()) {
            System.out.println(course);
        }
    }

    static void viewAllGrades() {
        for (Student student : studentService.findAll()) {
            System.out.println("Student: " + student.getFirstName() + " "
                    + student.getLastName());
            if (student.getGrades().isEmpty()) {
                System.out.println("No Grades!");
            } else {
                for (Map.Entry<Course, Integer> entry : student.getGrades().entrySet()) {
                    System.out.println("  Course: " + entry.getKey().getCourseName() +
                            " - Grade: " + entry.getValue());
                }
            }
            System.out.println("  GPA: " + GPAUtil.calculateGPA(student));
            System.out.println("---");
        }
    }

    static void reports() {
        System.out.println("========== REPORTS ==========");
        System.out.println("Total Students : " + studentService.findAll().size());
        System.out.println("Total Teachers : " + teacherService.findAll().size());
        System.out.println("Total Courses  : " + courseService.findAll().size());

        // Top Student (Stream API)
        System.out.println("\n--- Top Student ---");
        studentService.findAll().stream()
                .max(Comparator.comparing(s -> GPAUtil.calculateGPA(s)))
                .ifPresent(s -> System.out.println(s.getFirstName() + " " + s.getLastName()
                        + " - GPA: " + GPAUtil.calculateGPA(s)));

        // Students Ranked by GPA
        System.out.println("\n--- Students Ranked by GPA ---");
        studentService.findAll().stream()
                .sorted(Comparator.comparing((Student s) -> GPAUtil.calculateGPA(s)).reversed())
                .forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()
                        + " - GPA: " + GPAUtil.calculateGPA(s)));
    }

    static void searchStudent() {
        System.out.println("Enter Student Number: ");
        String studentNumber = input.next();
        studentService.findAll().stream()
                .filter(s -> s.getStudentNumber().equals(studentNumber))
                .findFirst()
                .ifPresentOrElse(
                        s -> System.out.println(s),
                        () -> System.out.println("Student not found!")
                );
    }
}
