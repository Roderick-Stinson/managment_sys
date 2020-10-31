package com.company.Client;

import com.company.Model.VehicleInfo;
import com.company.Server.Server;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class MainPage extends JPanel {
    public MyToolBar toolbar;
    public JScrollPane scrollPane;
    public JTable table;

    public MainPage() throws Exception {
        super(new BorderLayout());
        init();
        this.setVisible(true);
    }

    public void init() throws Exception {
        toolbar = new MyToolBar();
        this.add(this.toolbar, BorderLayout.NORTH);

        table = new JTable(new MyTableModel(Server.searchRecord(null,null,null,null,null)));
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
    private Object[] columnNames = {"carId", "carManufactory", "carModel", "carPrice", "isAvaiable"};
    private List<List<Object>> infoList;

    public MyTableModel(List<List<Object>> infoList) {
        this.infoList = infoList;
    }

    public void setInfoList(List<List<Object>> infoList) { this.infoList = infoList; }

    @Override
    public int getRowCount() {
        return infoList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) { return columnNames[column].toString(); }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return infoList.get(rowIndex).get(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }

    @Override
    public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
        infoList.get(rowIndex).set(columnIndex, newValue);
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
    public boolean flag;//true代表Add；flase代表Search\
    public List<List<Object>> infoList;
    public ViewHolder viewHolder;

    public GetVehicleInfo(Frame owner, Component parentComponent, boolean flag, ViewHolder viewHolder) {
        super(owner, "请输入相关信息", true);
        this.flag = flag;
        this.setSize(300,230);
        this.setLocationRelativeTo(parentComponent);

        panel = new JPanel(null);

        carId = new JLabel("车辆编号: ");
        carId.setBounds(35,20,80,25);
        panel.add(carId);
        IdInputBox = new JTextField(20);
        IdInputBox.setDocument(new NumberDocument());
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
        PriceInputBox.setDocument(new NumberDocument());
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
               if (flag) {//添加按钮的规则逻辑
                   if (Rules.determineAdd(getVehicleInfo(), carManufactory, carModel, carPrice, isAvaiable)) {
                       if (carId.equals(""))
                        setInfo(new VehicleInfo(carManufactory,carModel,Integer.valueOf(carPrice),isAvaiable));
                       else
                           setInfo(new VehicleInfo(Integer.valueOf(carId), carManufactory,carModel,Integer.valueOf(carPrice),isAvaiable));
                       try {
                           Server.addRecord(info.carManufactory, info.carModel, info.carPrice, info.isAvaialble);
                       } catch (Exception exception) {
                           exception.printStackTrace();
                       }
                       dispose();
                   }
               } else {//查询按钮的规则逻辑
                   try {
                       infoList = Server.searchRecord(carId.equals("") ? null : Integer.valueOf(carId), carManufactory.equals("") ? null : carManufactory, carModel.equals("") ? null : carModel, carPrice.equals("") ? null : Integer.valueOf(carPrice), isAvaiable);
                       viewHolder.setInfoList(infoList);
                   } catch (Exception exception) {
                       exception.printStackTrace();
                   }
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

    public List<List<Object>> getInfoList() { return this.infoList; }

}


//限制输入框内只能输入数字
class NumberDocument extends PlainDocument {

    public void insertString(int var1, String var2, AttributeSet var3) throws BadLocationException {
        if (this.isNumeric(var2)) {
            super.insertString(var1, var2, var3);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }

    }

    private boolean isNumeric(String var1) {
        try {
            Long.valueOf(var1);
            return true;
        } catch (NumberFormatException var3) {
            return false;
        }
    }
}

class Rules {

    public static boolean determineAdd(GetVehicleInfo getDialog, String carManufactory, String carModel, String carPrice, Boolean isAvaialble) {
        if (carManufactory.equals("")) {
            JOptionPane.showMessageDialog(getDialog, "请输入车辆厂家信息", "输入信息不全", 0);
            return false;
        } else if(carModel.equals("")) {
            JOptionPane.showMessageDialog(getDialog, "请输入车辆型号信息", "输入信息不全", 0);
            return false;
        } else if (carPrice.equals("")) {
            JOptionPane.showMessageDialog(getDialog,"请输入租赁价格/日", "输入信息不全", 0);
            return false;
        } if (isAvaialble == null) {
            JOptionPane.showMessageDialog(getDialog, "请选择是否可用", "输入信息不全", 0);
            return false;
        } else {
            JOptionPane.showMessageDialog(getDialog, "添加成功", "成功", 2);
            return true;
        }
    }
}


