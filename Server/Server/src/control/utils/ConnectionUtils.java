package control.utils;

import java.sql.Connection;

public class ConnectionUtils {
    public static Connection getMyConnection() throws Exception {
        return MySQLConnectionUtils.getMySQLConnection();
    }

//    // Test Connection ...
//    public static void main(String[] args) throws Exception {
//        System.out.println("Get connection ... ");
//        Connection connection = ConnectionUtils.getMyConnection();
//        System.out.println("Get connection " + connection);
//        System.out.println("Done!");
//    }

}
