package com.company.Client;

import com.company.Server.Server;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setSize(500, 500);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        LogIn logIn = new LogIn();
//        MainPage mainPage = new MainPage();
//        frame.add(mainPage);
//
//        logIn.btn_login.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (logIn.userInputBox.getText().equals("aaa") && String.valueOf(logIn.pwdInputBox.getPassword()).equals("bbb")) {
//                    frame.getContentPane().setVisible(false);
//                    frame.setContentPane(mainPage);
//                } else {
//                    JOptionPane.showMessageDialog(logIn, "用户名或密码错误","错误", 0);
//                }
//
//            }
//        });
//
//        mainPage.toolbar.btnAdd.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new GetVehicleInfo(frame, mainPage,true);
//            }
//        });
//
//        mainPage.toolbar.btnSearch.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new GetVehicleInfo(frame, mainPage, false);
//            }
//        });
//
//        TableModel tableModel = mainPage.table.getModel();
//        tableModel.addTableModelListener(new TableModelListener() {
//            @Override
//            public void tableChanged(TableModelEvent e) {
//                int firstrow = e.getFirstRow();
//                int lastRow = e.getLastRow();
//
//                int column = e.getColumn();
//                int type = e.getType();
//
//                System.out.println("影响了第"+firstrow+"行"+"第"+column+"列");
//
//
//            }
//        });
//
//        frame.setVisible(true);
//    }

    public static void main(String[] args) throws Exception {
        Server.addRecord();
    }


}