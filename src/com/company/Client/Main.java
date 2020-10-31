package com.company.Client;

import com.company.Server.Server;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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



//        mainPage.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                int firstRow = 0, lastRow = 0;
////                if (e.getValueIsAdjusting())
////                    firstRow = e.getFirstIndex();
////                else
////                    lastRow = e.getLastIndex();
//                firstRow = e.getFirstIndex();
//                lastRow = e.getLastIndex();
//            //    System.out.println("FirstIndex: " + firstRow + "LastIndex: " + lastRow);
//                System.out.println(mainPage.table.getSelectedColumnCount());
//
//            }
//        });

        mainPage.table.addMouseListener(new MouseAdapter() {
            int firstRow, rowCount;

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                firstRow = mainPage.table.getSelectedRow();
                System.out.println(firstRow);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                rowCount = mainPage.table.getSelectedRowCount();
                System.out.println(rowCount);

                mainPage.toolbar.btnDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = firstRow; i < firstRow+rowCount; ++i) {
                            int carId = (int) tableModel.getValueAt(i, 0);
                            try {
                                Server.deleteRecord(carId);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                    }
                });

            }


        });

        frame.setVisible(true);
    }



}