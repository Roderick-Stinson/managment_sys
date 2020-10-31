package com.company.Model;

import java.util.List;

public class ViewHolder {
    public List<List<Object>> infoList;
    public boolean flag;

    public ViewHolder(List<List<Object>> infoList, boolean flag) {
        this.infoList = infoList;
        this.flag = flag;
    }

    public void setInfoList(List<List<Object>> infoList) {
        this.infoList = infoList;
    }

    public void setFlag(boolean flag) { this.flag = flag; }
    public List<List<Object>> getInfoList() { return infoList; }

    public boolean getFlag() { return this.flag; }
}
