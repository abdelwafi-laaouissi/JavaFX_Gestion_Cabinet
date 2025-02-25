package ma.enset.gestionconsultationdbcc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection dbConnection;
    static {
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_cabinet","root","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() {
        return dbConnection;
    }
}
