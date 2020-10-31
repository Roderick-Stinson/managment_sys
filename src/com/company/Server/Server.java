package com.company.Server;

import com.company.Model.VehicleInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static int addRecord(String carManufacotry, String carModel, Integer carPrice, Boolean isAvaiable) throws Exception {
        DbUtil dbUtil = new DbUtil();
        Connection con = dbUtil.getCon();
        String sql = "INSERT INTO carList (carManufactory, carModel, carPrice, isAvaiable)" + "VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, carManufacotry);
        pstmt.setString(2,carModel);
        pstmt.setInt(3, carPrice);
        pstmt.setBoolean(4, isAvaiable);
        int rst = pstmt.executeUpdate();
        dbUtil.close(pstmt, con);
        return rst;
    }

    public static List<List<Object>> searchRecord() throws Exception {
        DbUtil dbUtil = new DbUtil();
        Connection con = dbUtil.getCon();

        List<List<Object>> infoList = new ArrayList<>();
        List<Object> info;
        String sql = "SELECT * FROM carList;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery(sql);
        while (rs.next()) {
            info = new ArrayList<>();
            int carId = rs.getInt(1);
            String carManufactory = rs.getString(2);
            String carModel = rs.getString(3);
            int carPrice = rs.getInt(4);
            boolean isAvaiable = rs.getBoolean(5);

            info.add(carId);
            info.add(carManufactory);
            info.add(carModel);
            info.add(carPrice);
            info.add(isAvaiable);

            infoList.add(info);
        }
        return infoList;

    }

    public static void updateRecord(String field, Object newValue, int carId) throws Exception {
        DbUtil dbUtil = new DbUtil();
        Connection con = dbUtil.getCon();
        String sql = "UPDATE carList SET " + field + "=?" + " WHERE carId=?;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        if (field.equals("carManufactory") || field.equals("carModel"))
            pstmt.setString(1, (String) newValue);
        else if (field.equals("carPrice"))
            pstmt.setInt(1, Integer.valueOf((String) newValue));
        else if (field.equals("isAvaiable"))
            pstmt.setBoolean(1, Boolean.valueOf((String) newValue));
        pstmt.setInt(2, carId);
        int rst = pstmt.executeUpdate();
        dbUtil.close(pstmt, con);

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
