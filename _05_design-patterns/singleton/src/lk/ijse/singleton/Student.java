package lk.ijse.singleton;

public class Student {
    int age;
    private static Student student;

    private Student () {

    }

    public static Student getInstance() {
        if (student == null) {
            student = new Student();
            return student;
        } else {
            return student;
        }
    }
}
