package com.example.securi.listView;

import androidx.annotation.Nullable;

public class ListViewItem_datainfo {
    String dataTitle;
    String dataDate;
    String dataInfoImg;


    public ListViewItem_datainfo(){}
    public ListViewItem_datainfo(String dataTitle, String dataDate, String dataInfoImg){
        this.dataTitle = dataTitle;
        this.dataDate = dataDate;
        this.dataInfoImg = dataInfoImg;
    }
    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public String getDataInfoImg() {
        return dataInfoImg;
    }

    public void setDataInfoImg(String dataInfoImg) {
        this.dataInfoImg = dataInfoImg;
    }
}
