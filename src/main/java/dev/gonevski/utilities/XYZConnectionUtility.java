package dev.gonevski.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class XYZConnectionUtility {

    private static final String password = System.getenv("API_PASSW");
    private static final String username = System.getenv("API_USERNAME");
    private static final String urlAWS = System.getenv("POSTGRES_AWS");
    private static final String connectionstring = urlAWS + "user=" + username + "&password=" + password;

    public static Connection createConnection(){
        try {
            Connection conn = DriverManager.getConnection(connectionstring);
            return conn;
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.WARNING);
            return null;
        }

    }
}
