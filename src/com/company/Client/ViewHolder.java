package com.company.Client;

import com.company.Model.VehicleInfo;

import java.util.List;

public class ViewHolder {
    public List<List<Object>> infoList;
    public ViewHolder(List<List<Object>> infoList) {
        this.infoList = infoList;
    }

    public void setInfoList(List<List<Object>> infoList) {
        this.infoList = infoList;
    }

    public List<List<Object>> getInfoList() { return infoList; }
}
