package com.company.Client;

import com.company.Server.Server;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LogIn logIn = new LogIn();
        MainPage mainPage = new MainPage();
        frame.add(mainPage);

        logIn.btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (logIn.userInputBox.getText().equals("aaa") && String.valueOf(logIn.pwdInputBox.getPassword()).equals("bbb")) {
                    frame.getContentPane().setVisible(false);
                    frame.setContentPane(mainPage);
                } else {
                    JOptionPane.showMessageDialog(logIn, "用户名或密码错误","错误", 0);
                }

            }
        });

        mainPage.toolbar.btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GetVehicleInfo(frame, mainPage,true);
            }
        });

        mainPage.toolbar.btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GetVehicleInfo(frame, mainPage, false);
            }
        });

        TableModel tableModel = mainPage.table.getModel();
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int firstRow = e.getFirstRow();
                int lastRos = e.getLastRow();
                int column = e.getColumn();
                int type = e.getType();

                if (type == TableModelEvent.UPDATE) {
                    try {
                        Server.updateRecord(tableModel.getColumnName(column), tableModel.getValueAt(firstRow, column), (Integer) tableModel.getValueAt(firstRow, 0));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    return;
                }
            }
        });

        frame.setVisible(true);
    }



}