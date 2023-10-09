package lk.ijse.statements;

/*
    @author DanujaV
    @created 10/9/23 - 11:22 AM   
*/

import java.sql.*;

public class CreateStatementDemo {
    private static void saveUser() throws SQLException {

        //1st step
        //        Class.forName("com.mysql.cj.jdbc.Driver"); //since JDBC 4.x not compulsory to load and register the Driver

        //2nd step
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/kade", "root", "Danu25412541@");

        //3rd step
        Statement statement = connection.createStatement();

        //4th step
        String sql = "INSERT INTO user VALUES('U004', 'Malith', 'mali1234', '0765567867')";
        int affectedRows = statement.executeUpdate(sql);

        System.out.println(affectedRows > 0 ? "user inserted!" : "oops! something happened!");

        //5th step
        connection.close();
    }

    private static void loadAllUsers() throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/kade", "root", "Danu25412541@");

        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM user";
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            String userName = resultSet.getString("user_name");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String tel = resultSet.getString("tel");

            System.out.println(userName + " - " + name + " - " + password + " - " + tel);
        }

    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        saveUser();

        loadAllUsers();
    }
}
