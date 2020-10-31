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

    public static List<List<Object>> searchRecord(Integer carIdIn, String carManufactoryIn, String carModelIn, Integer carPriceIn, Boolean isAvaiableIn) throws Exception {
        DbUtil dbUtil = new DbUtil();
        Connection con = dbUtil.getCon();

        List<List<Object>> infoList = new ArrayList<>();
        List<Object> info;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM carList WHERE 1=1 ");
        if (carIdIn != null) {
            //输入carId
            sql.append("AND carId=" + carIdIn + " ");
        } else {
            if (carManufactoryIn != null) {
                //输入厂商
                sql.append("AND carManufactory=" + carManufactoryIn + " ");
            }
            if (carModelIn != null) {
                //输入型号
                sql.append("AND carModel=" + carModelIn + " ");
            }
            if (carPriceIn != null) {
                //输入价格
                sql.append("AND carPrice=" + carPriceIn + " ");
            }
            if (isAvaiableIn != null) {
                //输入可用性
                sql.append("AND isAvaiable=" + isAvaiableIn + " ");
            }
        }
        sql.append(";");

        PreparedStatement pstmt = con.prepareStatement(sql.toString());
        ResultSet rs = pstmt.executeQuery();
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

    public static void deleteRecord(int carId) throws Exception {
        DbUtil dbUtil = new DbUtil();
        Connection con = dbUtil.getCon();
        String sql = "DELETE FROM carList WHERE carId=?;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, carId);
        int rst = pstmt.executeUpdate();
        dbUtil.close(pstmt, con);
    }

}
