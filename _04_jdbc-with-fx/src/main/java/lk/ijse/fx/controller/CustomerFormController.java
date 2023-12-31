package lk.ijse.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.*;

public class CustomerFormController {
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableView<?> tblCustomer;

    public void initialize() {
        loadAllCustomer();
    }

    private void loadAllCustomer() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kade2",
                    "root",
                    "Sachi@123"
            );

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

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kade2",
                    "root",
                    "Sachi@123"
            );

            String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, id);
            pstm.setString(2, name);
            pstm.setString(3, address);
            pstm.setString(4, tel);

            int affectedRows = pstm.executeUpdate();

            if(affectedRows > 0) {
                clearFields();
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!!").show();
            }

            connection.close();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kade2",
                    "root",
                    "Sachi@123"
            );

            String sql = "SELECT * FROM customer WHERE id=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);

            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()) {
                String cus_id = resultSet.getString(1);
                String cus_name = resultSet.getString(2);
                String cus_address = resultSet.getString(3);
                String cus_tel = resultSet.getString(4);

                txtName.setText(cus_name);
                txtAddress.setText(cus_address);
                txtTel.setText(cus_tel);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "cutomer not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
