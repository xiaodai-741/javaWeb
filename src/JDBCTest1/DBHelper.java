package JDBCTest1;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    public static  String DRIVER;
    public static  String URL;
    public static  String USER;
    public static  String PASSWORD;
    static {
        Properties pro = new Properties();
        InputStream in = DBHelper.class.getClassLoader().getResourceAsStream("JDBCTest1/db.properties");
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DRIVER = pro.getProperty("driver");
        URL = pro.getProperty("url");
        USER = pro.getProperty("user");
        PASSWORD = pro.getProperty("password");
    }
    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

}
