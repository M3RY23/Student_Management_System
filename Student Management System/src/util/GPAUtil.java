package util;

import entity.Student;

public class GPAUtil {
    public static double calculateGPA(Student student) {
        if (student.getGrades().isEmpty()) return 0.0;

        double total = 0;
        for (Integer grade : student.getGrades().values()) {
            if (grade >= 90)
                total += 4.0;
            else if (grade >= 80)
                total += 3.0;
            else if (grade >= 70)
                total += 2.0;
            else if (grade >= 60)
                total += 1.0;
        }
        return total / student.getGrades().size();
    }
}

