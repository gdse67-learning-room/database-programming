package lk.ijse.fx.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;
    private Connection connection;
    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/kade2",
                "root",
                "Sachi@123"
        );
    }

    public static DbConnection getInstance() throws SQLException {
        return (null == dbConnection) ? dbConnection = new DbConnection() : dbConnection;
        /*if(dbConnection == null) {
            dbConnection = new DbConnection();
            return dbConnection;
        } else {
            return dbConnection;
        }*/
    }

    public Connection getConnection() {
        return connection;
    }
}
