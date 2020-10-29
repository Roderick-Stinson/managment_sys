package com.company.Client;

import javax.swing.*;

public class Search extends JPanel {
    private JLabel record;

    public Search() {
        super(null);
        init();
        this.setVisible(true);
    }

    public void init() {
        record = new JLabel("Second Panel");
        this.record.setBounds(100,50,165,25);
        this.add(record);
    }

    public void show_panel() {
        this.setVisible(true);
    }
}
