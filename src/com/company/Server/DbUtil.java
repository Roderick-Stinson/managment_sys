package com.company.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbUtil {
    //CarManagment为数据库名，改成自己的数据库名即可
    //USerName和PWD为登录数据库的用户和密码，此处改为自己的
    //运行前需添加数据库依赖
    private static final String URL = "jdbc:mysql://localhost:3306/CarManagment";
    private static final String jdbcName = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PWD = "Wangzihan0508";

    public Connection getCon() throws Exception {
        Class.forName(jdbcName);
        Connection con = DriverManager
                .getConnection(URL, USERNAME, PWD);
        return con;
    }

    public void close(Statement stmt, Connection con) throws Exception {
        if (stmt != null) {
            stmt.close();
            if (con != null) {
                con.close();
            }
        }
    }

}
