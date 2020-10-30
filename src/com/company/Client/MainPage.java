package com.company.Client;

import com.company.Model.VehicleInfo;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JPanel {
    public MyToolBar toolbar;
    public JScrollPane scrollPane;
    public JTable table;

    public MainPage() {
        super(new BorderLayout());
        init();
        this.setVisible(true);
    }

    public void init() {
        toolbar = new MyToolBar();
        this.add(this.toolbar, BorderLayout.NORTH);

        table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(400, 300));

        this.scrollPane = new JScrollPane(table);
        this.add(this.scrollPane, BorderLayout.CENTER);

    }

}

class MyToolBar extends JPanel {
    public JButton btnAdd, btnDelete, btnSearch;

    public MyToolBar() {
        super(new GridLayout(1,3,20,20));

        btnAdd = new JButton("增加");
        this.add(btnAdd);
        btnDelete = new JButton("删除");
        this.add(btnDelete);
        btnSearch = new JButton("查询");
        this.add(btnSearch);

    }
}

class MyTableModel extends AbstractTableModel {
    private Object[] columnNames = {"车辆编号", "生产厂商", "车辆型号", "租赁价格/日", "是否可用"};
    private Object[][] data = {
            {"111", "BMW", "A6", "500", "是"},
            {"112", "BMW", "A6", "500", "是"},
            {"113", "BMW", "A6", "500", "否"},
            {"114", "BMW", "A6", "500", "是"},
            {"115", "BMW", "A6", "500", "是"},
            {"116", "BMW", "A6", "500", "是"},
            {"117", "BMW", "A6", "500", "是"},
            {"118", "BMW", "A6", "500", "是"},
            {"119", "BMW", "A6", "500", "是"},
            {"120", "BMW", "A6", "500", "是"},
            {"121", "BMW", "A6", "500", "是"},
            {"122", "BMW", "A6", "500", "是"},
            {"123", "BMW", "A6", "500", "是"},
            {"124", "BMW", "A6", "500", "是"},
            {"125", "BMW", "A6", "500", "是"},
            {"126", "BMW", "A6", "500", "是"},
            {"127", "BMW", "A6", "500", "是"},
            {"128", "BMW", "A6", "500", "是"},
            {"129", "BMW", "A6", "500", "是"},
            {"130", "BMW", "A6", "500", "是"},
            {"131", "BMW", "A6", "500", "是"},
            {"132", "BMW", "A6", "500", "是"},
            {"133", "BMW", "A6", "500", "是"},
            {"134", "BMW", "A6", "500", "是"},
            {"135", "BMW", "A6", "500", "是"},
            {"136", "BMW", "A6", "500", "是"},
            {"137", "BMW", "A6", "500", "是"},
            {"138", "BMW", "A6", "500", "是"},
            {"139", "BMW", "A6", "500", "是"},
            {"140", "BMW", "A6", "500", "是"},
    };
    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column].toString();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex!=0;
    }

    @Override
    public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = newValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}

class GetVehicleInfo extends JDialog {
    public JPanel panel;
    public JLabel carId, carManufactory, carModel, carPrice, ifAvailable;
    public JTextField IdInputBox, ManInputBox, ModelInputBox, PriceInputBox;
    public JRadioButton avaiable, unavaiable;
    public ButtonGroup group;
    public JButton btnConfirm;
    public VehicleInfo info;
    public boolean flag;//true代表Add；flase代表Search

    public GetVehicleInfo(Frame owner, Component parentComponent, boolean flag) {
        super(owner, "请输入相关信息", true);
        this.flag = flag;
        this.setSize(300,230);
        this.setLocationRelativeTo(parentComponent);

        panel = new JPanel(null);

        carId = new JLabel("车辆编号: ");
        carId.setBounds(35,20,80,25);
        panel.add(carId);
        IdInputBox = new JTextField(20);
        IdInputBox.setBounds(100,20,165,25);
        panel.add(IdInputBox);

        carManufactory = new JLabel("车辆厂家: ");
        carManufactory.setBounds(35,50,80,25);
        panel.add(carManufactory);
        ManInputBox = new JTextField(20);
        ManInputBox.setBounds(100,50,165,25);
        panel.add(ManInputBox);

        carModel = new JLabel("车辆型号: ");
        carModel.setBounds(35, 80, 80, 25);
        panel.add(carModel);
        ModelInputBox = new JTextField(20);
        ModelInputBox.setBounds(100, 80, 165, 25);
        panel.add(ModelInputBox);

        carPrice = new JLabel("租赁价格: ");
        carPrice.setBounds(35,110,80,25);
        panel.add(carPrice);
        PriceInputBox = new JTextField(20);
        PriceInputBox.setBounds(100, 110, 165, 25);
        panel.add(PriceInputBox);

        ifAvailable = new JLabel("是否可用: ");
        ifAvailable.setBounds(35,140,80,25);
        panel.add(ifAvailable);
        avaiable = new JRadioButton("是");
        avaiable.setBounds(110,140,50,25);
        unavaiable = new JRadioButton("否");
        unavaiable.setBounds(190, 140, 50, 25);

        group = new ButtonGroup();
        group.add(avaiable);
        group.add(unavaiable);
        panel.add(avaiable);
        panel.add(unavaiable);

        btnConfirm = new JButton("确认");
        btnConfirm.setBounds(80, 175, 120,25);
        panel.add(btnConfirm);
        this.btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String carId = IdInputBox.getText();
                String carManufactory = ManInputBox.getText();
                String carModel = ModelInputBox.getText();
                String carPrice = PriceInputBox.getText();
                Boolean isAvaiable = null;
                for (Component c:panel.getComponents()) {
                    if (c instanceof JRadioButton) {
                        if (((JRadioButton)c).isSelected()) {
                            if (((JRadioButton)c).getText().equals("是"))
                                isAvaiable = true;
                            else
                                isAvaiable = false;
                        }
                    }
                }
                setInfo(new VehicleInfo(carId, carManufactory,carModel,carPrice,isAvaiable));
               if (flag) {
                   if (Rules.determineAdd(getVehicleInfo(), info))
                       dispose();
               } else {
                   dispose();
               }

            }
        });

        this.setContentPane(panel);
        this.setVisible(true);
    }

    public void setInfo(VehicleInfo info) {
        this.info = info;
    }

    public GetVehicleInfo getVehicleInfo() {
        return this;
    }

}

class Rules {

    public static boolean determineAdd(GetVehicleInfo getDialog, VehicleInfo info) {
        if (info.carManufactory.equals("")) {
            JOptionPane.showMessageDialog(getDialog, "请输入车辆厂家信息", "输入信息不全", 0);
            return false;
        } else if(info.carModel.equals("")) {
            JOptionPane.showMessageDialog(getDialog, "请输入车辆型号信息", "输入信息不全", 0);
            return false;
        } else if (info.carPrice.equals("")) {
            JOptionPane.showMessageDialog(getDialog,"请输入租赁价格/日", "输入信息不全", 0);
            return false;
        } if (info.isAvaialble == null) {
            JOptionPane.showMessageDialog(getDialog, "请选择是否可用", "输入信息不全", 0);
            return false;
        } else {
            JOptionPane.showMessageDialog(getDialog, "添加成功", "成功", 2);
            return true;
        }
    }
}


