package control.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionUtils {

    public static Connection getMySQLConnection() throws Exception {
        String hostName = "localhost";
        String dbName = "h_dev";
        String userName = "root";
        String password = "123456789";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?zeroDateTimeBehavior=convertToNull";
        Connection connection = DriverManager.getConnection(connectionURL, userName, password);
        return connection;
    }
}
