package com.test.util;

import java.sql.*;


public class DbUtil {
    /**
     * 数据库连接
     * @return 返回java.sql.Connection接口类型的实例
     * @throws ClassNotFoundException 当数据库驱动加载失败
     * @throws SQLException SQLException 数据库连接失败
     * @author thechen
     */
    private static String url = "jdbc:mysql://localhost:3306/db1112?characterEncoding=utf8&useSSL=false&severTimezone=Asia/Shanghai";
    private static String username= "root";
    private static String password = "";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //加载数据库驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获得数据库连接对象
        Connection con = DriverManager.getConnection(url,username,password);
        //关闭数据库自动提交功能
        con.setAutoCommit(false);
        //返回数据库连接对象
        return con;
    }

    /**
     * 关闭与数据库相关的对象
     *
     * @param rs 结果集
     * @param ps 语句对象或准备语句对象
     * @param con 数据库连接对象
     * @throws SQLException 关闭数据库相关对象失败
     * @author 陈俊楷
     */

    public static void close(ResultSet rs,Statement ps,Connection con) throws SQLException{
        if(rs != null){
            rs.close();
        }
        if(ps != null){
            ps.close();
        }
        if(con != null){
            con.close();
        }
    }

}
