package com.example.securi.listView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.securi.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    public ListViewAdapter(){

    }
    public int getCount(){
        return listViewItemList.size();
    }

    public View getView(int position,View convertView,ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.securi_entryhistory_listview, parent, false);
        }
        TextView calDate = (TextView) convertView.findViewById(R.id.calDate);
        ListViewItem listViewItem = listViewItemList.get(position);
        calDate.setText(listViewItem.getCalDate());
        return convertView;
    }
        public long getItemId(int position){
            return position;
        }
        public Object getItem(int position){
        return listViewItemList.get(position);
    }
    public void addItem(String date){
        ListViewItem item = new ListViewItem();
        item.setCalDate(date);
        listViewItemList.add(item);
    }
}