package lk.ijse.statements;

/*
    @author DanujaV
    @created 10/9/23 - 12:15 PM   
*/

import java.sql.*;

public class SQLInjection {
    private static void searchUserByIdWithCreateStatement(String user_name) throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/kade", "root", "Danu25412541@");

        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM user WHERE user_name = " + user_name;

        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            String userName = resultSet.getString(1);
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);
            String tel = resultSet.getString(4);

            System.out.println(userName + " - " + name + " - " + password + " - " + tel);
        }

        connection.close();
    }

    private static void searchUserByIdWithPrepareStatement(String user_name) throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/kade", "root", "Danu25412541@");

        String sql = "SELECT * FROM user WHERE user_name=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, user_name);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            String useName = resultSet.getString(1);
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);
            String tel = resultSet.getString(4);

            System.out.println(useName + " - " + name + " - " + password + " - " + tel);
        }

        connection.close();
    }

    public static void main(String[] args) throws SQLException {
        searchUserByIdWithCreateStatement("'U001 OR 1=1'");

        searchUserByIdWithPrepareStatement("U001 OR 1=1");
    }

}
