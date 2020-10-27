package JDBCTest1;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * 程序方法实现
 */
public class JDBCText1 {
    Scanner sca = new Scanner(System.in);

    /**
     * JDBC准备
     *
     * @param sql 需要执行的SQL语句
     * @return PreparedStatement 通道
     * @throws ClassNotFoundException 类无法找到
     * @throws SQLException           sql语句错误
     */
    private PreparedStatement execute(String sql) throws ClassNotFoundException, SQLException {
        Connection conn = DBHelper.getConn();
//        //找驱动
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        //建连接
//        String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
//        String user = "scott";
//        String password = "123456";
//        Connection conn = DriverManager.getConnection(url, user, password);
        //建通道
        return conn.prepareStatement(sql);

    }

    /**
     * SQL DML语句执行 返回结果
     *
     * @param sql 需要执行的SQL语句
     */
    @SuppressWarnings("unused")
    private void executeUpdate(String sql) {
        try {
            PreparedStatement pst = execute(sql);
            int i = pst.executeUpdate();
            if (i > 0) {
                System.out.println("操作成功");
                System.out.println("按任意键结束");
                @SuppressWarnings("unused")
                String ss = sca.next();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * SQL DQL语句执行结果
     *
     * @param sql 需要执行的SQL语句
     * @return 查找结果集
     */
    private List<Dept> executeQuery(String sql) {
        List<Dept> list = new ArrayList<>();
        try {
            PreparedStatement pst = execute(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rstmd = rs.getMetaData();
            int cont = rstmd.getColumnCount();
            while (rs.next()) {
//                Dept[] obj = new Dept[cont];
//                for (int i = 0; i < cont; i++) {
                Dept d = new Dept();
                d.setDeptno(rs.getInt("DEPTNO"));
                d.setDname(rs.getString("DNAME"));
                d.setLoc(rs.getString("LOC"));
//                }
//                System.out.println(d.toString());
                list.add(d);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    private List<Object[]> executeQuery1(String sql) {
        List<Object[]> list = new ArrayList<>();
        try {
            PreparedStatement pst = execute(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rstmd = rs.getMetaData();
            int cont = rstmd.getColumnCount();
            while (rs.next()) {
                Object[] obj = new Object[cont];
                for (int i = 0; i < cont; i++) {
                    obj[i] = rs.getObject(i + 1);
                }

                list.add(obj);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * 查找数据库 包含表
     */
    private List<Object[]> findTable() {
        String sql = "select table_name from user_tables";
        List<Object[]> list = executeQuery1(sql);
        printResult1(list);
        return list;
    }

    /**
     * 系统功能 主菜单
     */
    public void menu() {
        System.out.println("welcome to database Management System");
        String tableName = null;
        boolean message = true;
        while (message) {
            System.out.println("当前数据库为cott，包含表为：");
            List<Object[]> tableList = findTable();
            System.out.println("请输入需要操作的表：");
            tableName = sca.next();

            for (Object[] ob : tableList) {
                if (tableName.equalsIgnoreCase((String) ob[0])) {
                    message = false;
                    break;
                }

            }
            if (message) {
                System.out.println("没有找到这个表，请重新输入：");
            }
        }
        while (true) {
            System.out.println("当前表为" + tableName.toUpperCase());
            findAll(tableName);
            System.out.println("请输入你要执行的操作：1.查询 2.增添 3.删除 4.修改 5.退出");
            int chooseMessage = 0;
            try {
                Scanner sca1 = new Scanner(System.in);
                chooseMessage = sca1.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("请输入正确的指令");
            }
            switch (chooseMessage) {
                case 1 -> find(tableName);
                case 2 -> add();
                case 3 -> delete(tableName);
                case 4 -> update();
                case 5 -> System.exit(0);
                default -> System.out.println("请输入正确的指令");
            }
        }
    }

    /**
     * 查询单个数据
     *
     * @param tableName 操作表
     */
    public void find(String tableName) {
        System.out.println("请输入 您要查询的编码：");

        int no;
        try {
            no = sca.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("请输入正确的指令");
            return;
        }
        String sql = "select * from " + tableName + " where deptno = " + no + " ";
//        System.out.println(sql);
        List<Dept> list = executeQuery(sql);
        printResult(list);
        System.out.println("按任意键继续");
        @SuppressWarnings("unused")
        String qq = sca.next();

    }

    /**
     * 增添数据
     */
    private void add() {
        System.out.println("请输入要插入的部门编号：");
        int deptno = sca.nextInt();
        System.out.println("请输入要插入的部门名称：");
        String dname = sca.next();
        System.out.println("请输入要插入的部门地址：");
        String loc = sca.next();
        String sql = "insert into dept values (" + deptno + ",'" + dname + "','" + loc + "')";
//        System.out.println(sql);
        executeUpdate(sql);
    }

    /**
     * 删除数据
     *
     * @param tableName 操作表
     */
    private void delete(String tableName) {
        System.out.println("请输入要删除的编号：");
        int deptno = 0;
        try {
            deptno = sca.nextInt();
        } catch (Exception e) {
            System.out.println("请输入正确的数字");
        }
        String sql = "delete  from " + tableName + " where deptno = " + deptno;
//        System.out.println(sql);
        executeUpdate(sql);
    }

    private void update() {
        System.out.println("请输入要修改的编号：");
        int deptno = 0;
        try {
            deptno = sca.nextInt();
        } catch (Exception e) {
            System.out.println("请输入正确的数字");
        }
        System.out.println("请输入想要修改的属性：");
        String message = sca.next();
        System.out.println("请输入想要修改的内容：");
        String message2 = sca.next();
        String sql = "update dept set " + message + " = " + "'" + message2 + "' where deptno = " + deptno;
//        System.out.println(sql);
        executeUpdate(sql);
    }

    /**
     * sql语句:查询所有数据
     */
    public void findAll(String tableName) {
        String sql = "select * from " + tableName;
//        System.out.println(sql);
        List<Dept> list = executeQuery(sql);
        printResult(list);
    }

    /**
     * 遍历结果集
     */
    public void printResult(List<Dept> list) {
        if (list != null) {
            for (Dept obj : list) {
                obj.toString();
                System.out.println();
            }

        }
    }

    public void printResult1(List<Object[]> list) {
        if (list != null) {
            for (Object[] obj : list) {
                for (Object ob : obj) {
                    System.out.print(ob + "\t");
                }
                System.out.println();
            }
        }
    }
}

