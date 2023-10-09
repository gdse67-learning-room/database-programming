package lk.ijse.maven;

/*
    @author DanujaV
    @created 10/9/23 - 12:47 PM   
*/

import java.sql.*;

public class AppInitializer {
    private static void saveCustomer(String id, String name, String address, String tel) throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/kade", "root", "Danu25412541@");

        String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);
        pstm.setString(2, name);
        pstm.setString(3, address);
        pstm.setString(4, tel);

        int affectedRows = pstm.executeUpdate();

        System.out.println(affectedRows > 0 ? "customer saved!" : "oops! something went wrong!");
        connection.close();
    }

    private static void loadAllCustomer() {
        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kade", "root", "Danu25412541@");

            String sql = "SELECT * FROM customer";
            PreparedStatement pstm = connection.prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String tel = resultSet.getString(4);

                System.out.println(id + " - " + name + " - " + address + " - " + tel);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteCustomer(String id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kade", "root", "Danu25412541@");

            String sql = "DELETE FROM customer WHERE customer_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, id);

            int affectedRows = pstm.executeUpdate();

            System.out.println(affectedRows > 0 ? "customer deleted!" : "oops! something happened!");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void updateCustomer(String id, String new_address, String new_tel) {
        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kade", "root", "Danu25412541@");

            String sql = "UPDATE customer SET address = ?, tel = ? WHERE customer_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, new_address);
            pstm.setString(2, new_tel);
            pstm.setString(3, id);

            int affectedRows = pstm.executeUpdate();

            System.out.println(affectedRows > 0 ? "customer updated!" : "oops! something happened!");

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void searchById(String id) {
        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/kade", "root", "Danu25412541@");

            String sql = "SELECT * FROM customer WHERE customer_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);

            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()) {
                String customerId = resultSet.getString(1);
                String customer_name = resultSet.getString(2);
                String customer_address = resultSet.getString(3);
                String customer_tel = resultSet.getString(4);

                System.out.println(customerId + " - " + customer_name + " - " + customer_address + " - " + customer_tel);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
//        saveCustomer("C002", "Kasun", "Galle", "0745896254");

        loadAllCustomer();

//        deleteCustomer("C001");

        updateCustomer("C001", "Jaffna", "0785642115");

        searchById("C001");
    }
}
