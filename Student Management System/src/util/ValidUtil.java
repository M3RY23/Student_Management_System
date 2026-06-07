package util;

public class ValidationUtil {

    public static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public static boolean isValidStudentNumber(String number) {
        return number.startsWith("ST") && number.length() >= 4;
    }

    public static boolean isValidName(String name) {
        return name.length() >= 2 && name.matches("[a-zA-Z]+");
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 4;
    }

    public static boolean isValidId(long id) {
        return id > 0;
    }
}