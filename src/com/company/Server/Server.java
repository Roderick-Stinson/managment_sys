package com.company.Server;

import com.company.Model.VehicleInfo;

import java.sql.*;

public class Server {

    public static int addRecord() throws Exception {
        DbUtil dbUtil = new DbUtil();
        Connection con = dbUtil.getCon();
        String sql = "INSERT INTO userList (userName, password, administrators)" + "VALUES (?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, "zhaoying");
        pstmt.setString(2,"001116");
        pstmt.setBoolean(3, true);
        int rst = pstmt.executeUpdate();
        dbUtil.close(pstmt, con);
        return rst;
    }


    public static void getMetaData() throws Exception {
        DbUtil dbUtil = new DbUtil();
        Connection con = dbUtil.getCon();
        String sql = "SELECT count(*)  FROM userList;";
        PreparedStatement pstat = con.prepareStatement(sql);
        ResultSetMetaData rsmd = pstat.getMetaData();//获取结果集元数据
        //DatabaseMetaData dbmd = con.getMetaData();
        int count = rsmd.getColumnCount(); //获取结果集元数据列数
        System.out.println("表一共有：" + count + "列");
        //遍历属性名称
        for (int i = 1; i <= count; i ++){
            System.out.println("第" + i + "行的属性为：" + rsmd.getColumnName(i)
                    + "，类型为：" + rsmd.getColumnTypeName(i));
        }
    }
}
