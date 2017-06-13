package com.gs.common.entity;

/**
 * Created by Administrator on 2017-04-11.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//本类专门用来连接数据库,可以作为固定的工具类使用(记下来即可)
public class DBManager {
    // 定义一个静态的连接对象用来连接数据库
    // private static Connection conn = null;
    // 定一个静态的语句对象,用来执行sql语句
    // private static Statement stmt = null;
    // 定义一个静态的结果集对象用来存放执行sql语句后查询得到的结果
    // private static ResultSet rs = null;

    /**
     * 连接数据库的方法
     *
     * @return conn 返回一个连接对象
     */
    public static Connection mssql(String url, String user, String pass) {
        Connection conn = null;
        try {
            // 1、加载连接驱动
            // "jdbc:odbc:bookdemo"
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            // 2、连接到数据库（获得连接对象）
            // 通过连接管理器(DriverManager)类的一个方法来获得连接对象，里面的参数表示我们连接到数据源bookdemo
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            // 以堆栈的方式将错误信息打印出来
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn; // 将连接对象返回
    }

    /**
     * 连接数据库的方法
     *
     * @return conn 返回一个连接对象
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection mysql(String url, String user, String pass)
            throws ClassNotFoundException, SQLException {
        Connection conn = null;

        // 1、加载连接驱动
        // "jdbc:odbc:bookdemo"
        Class.forName("com.mysql.jdbc.Driver");
        // 2、连接到数据库（获得连接对象）
        // 通过连接管理器(DriverManager)类的一个方法来获得连接对象，里面的参数表示我们连接到数据源bookdemo
        conn = DriverManager.getConnection(url, user, pass);

        return conn; // 将连接对象返回
    }

    /**
     * 动漫网的mysql数据库连接
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection mysql(String host, String database, String user,
                                   String pass) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://" + host + "/" + database
                + "?useUnicode=true&characterEncoding=UTF-8";
        return mysql(url, user, pass);
    }

    /**
     * 本函数用来执行用户传入的sql语句(仅限于select语句)
     *
     * @param sql
     *            传入的sql语句，等待执行
     * @return 返回执行sql语句后的结果集对象
     */
    public static ResultSet query(Connection conn, String sql) {
        ResultSet rs = null;
        try {
            // 3、通过连接对象创建一个语句对象stmt，用来执行sql语句
            Statement stmt = conn.createStatement();
            // 4、执行sql语句，得到一个rs（结果集对象）
            rs = stmt.executeQuery(sql);
        } catch (Exception e) { // 错误处理，暂时不用理会
            e.printStackTrace();
        }
        return rs; // 将查询得到的结果集对象返回
    }

    /**
     * 本方法用来执行更新语句，并返回影响了多少行（insert,update,delete）
     *
     * @param sql
     *            传入的sql语句，等待执行
     * @return 返回执行sql语句后的结果集对象
     */
    public static int update(Connection conn, String sql) {
        // 执行sql语句前先连接到数据库
        Statement stmt = null;
        int i = 0;
        try {
            // 通过连接对象创建一个语句对象stmt，用来执行sql语句
            stmt = conn.createStatement();
            // 执行更新语句，并返回影响了多少行
            i = stmt.executeUpdate(sql);
        } catch (Exception e) { // 错误处理，暂时不用理会
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
