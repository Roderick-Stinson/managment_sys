package com.company.Client;

import com.company.Model.ViewHolder;
import com.company.Server.Server;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

     public static void main(String[] args) throws Exception {
         ViewHolder viewHolder = new ViewHolder(null, false);

         JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LogIn logIn = new LogIn();
        MainPage mainPage = new MainPage(viewHolder);
        frame.add(logIn);

        logIn.btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = logIn.userInputBox.getText();
                String pwd = String.valueOf(logIn.pwdInputBox.getPassword());

                try {
                    if (Server.searchUser(userName, pwd) == null) {
                        JOptionPane.showMessageDialog(logIn, "用户名或密码错误", "错误", 0);
                    } else if (Server.searchUser(userName, pwd)) {
                        //超级管理员
                        viewHolder.setFlag(true);
                        frame.getContentPane().setVisible(false);
                        frame.setContentPane(mainPage);
                    } else {
                        //普通用户
                        viewHolder.setFlag(false);
                        frame.getContentPane().setVisible(false);
                        frame.setContentPane(mainPage);
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });

        mainPage.toolbar.btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (viewHolder.getFlag())
                    new GetVehicleInfo(frame, mainPage, viewHolder);
                else
                    return;
            }
        });

        mainPage.toolbar.btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GetVehicleInfo(frame, mainPage, viewHolder);
                mainPage.table.setModel(new MyTableModel(viewHolder.getInfoList(), viewHolder.getFlag()));
                mainPage.table.repaint();
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

        mainPage.table.addMouseListener(new MouseAdapter() {
            int firstRow, rowCount;

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                firstRow = mainPage.table.getSelectedRow();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                rowCount = mainPage.table.getSelectedRowCount();

                mainPage.toolbar.btnDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (viewHolder.getFlag()) {
                            for (int i = firstRow; i < firstRow + rowCount; ++i) {
                                int carId = (int) tableModel.getValueAt(i, 0);
                                try {
                                    Server.deleteRecord(carId);
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                        } else
                            return;
                    }
                });
            }
        });

        frame.setVisible(true);
    }



}