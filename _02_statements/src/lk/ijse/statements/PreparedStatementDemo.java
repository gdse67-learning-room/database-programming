package lk.ijse.statements;

/*
    @author DanujaV
    @created 10/9/23 - 11:56 AM   
*/

import java.sql.*;

public class PreparedStatementDemo {
    private static void getUserById(String user_name) throws SQLException {
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
        getUserById("U001");
    }
}
