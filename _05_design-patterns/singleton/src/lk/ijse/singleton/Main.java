package lk.ijse.singleton;

public class Main {
    public static void main(String[] args) {
//        Student s1 = new Student();
//        Student s2 = new Student();
//        Student s3 = new Student();

        Student s1 = Student.getInstance();
        Student s2 = Student.getInstance();
        Student s3 = Student.getInstance();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
