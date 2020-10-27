package StudentManagerSystem.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBHelper {
    private static final String DRIVER;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    public static ComboPooledDataSource cpds;

    static {
        Properties pro = new Properties();
        InputStream in = DBHelper.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DRIVER = pro.getProperty("DRIVER");
        URL = pro.getProperty("URL");
        USER = pro.getProperty("USER");
        PASSWORD = pro.getProperty("PASSWORD");

        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(DRIVER);//添加驱动字段
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl(URL);
        cpds.setUser(USER);
        cpds.setPassword(PASSWORD);

        cpds.setInitialPoolSize(10);//设置初始连接数
        cpds.setAcquireIncrement(5);//每次不足时添加5个连接
        cpds.setMaxPoolSize(40);//设置最大连接数
    }

//    /**
//     * 获得 connection 连接
//     * @return Connection 连接
//     * @throws ClassNotFoundException
//     * @throws SQLException
//     */
//    public static  Connection getconn() throws ClassNotFoundException, SQLException {
//        //找驱动
//        Class.forName(DRIVER);
//        //建连接
//        return DriverManager.getConnection(URL,USER,PASSWORD);
//    }
}
