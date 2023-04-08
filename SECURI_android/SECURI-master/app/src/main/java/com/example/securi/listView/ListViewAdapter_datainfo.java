package com.example.securi.listView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.securi.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ListViewAdapter_datainfo extends BaseAdapter {
    Context context;
    ArrayList<ListViewItem_datainfo> list;
    LayoutInflater inflater;
    AlertDialog dialog;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private DatabaseReference mReference2;
    private ChildEventListener mChild;
    Bitmap bitmap;
    public ListViewAdapter_datainfo(){

    }
    public ListViewAdapter_datainfo(Context context, ArrayList<ListViewItem_datainfo> list){
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public void addDTO(String picDate, String picLink) {
        ListViewItem_datainfo item_datainfo = new ListViewItem_datainfo();
        item_datainfo.setDataDate(picDate);
        item_datainfo.setDataInfoImg(picLink);
        list.add(item_datainfo);
    }

    //리스트의 항목을 삭제할 메서드 작성
    public void delDTO(int position) {
        list.remove(position);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final InfoViewHolder viewHolder;
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("1ZJbhzSqJ457JgqfCwJ1s5lsDurmTmWNNAXc8ILxotkM/sheet");
        mReference2 = mDatabase.getReference("ESP32_Test/Push/Timestamp");
        if (convertView == null) {
                convertView = inflater.inflate(R.layout.securi_datainfo_listview, parent, false);
                viewHolder = new InfoViewHolder();
                viewHolder.date = convertView.findViewById(R.id.dataDate);
                viewHolder.image = convertView.findViewById(R.id.dataInfoImg);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (InfoViewHolder) convertView.getTag();
        }
        ListViewItem_datainfo dto = list.get(position);
        String date = dto.getDataDate();
        final String resId = dto.getDataInfoImg();
        viewHolder.date.setText(date);
        viewHolder.date.setGravity(Gravity.CENTER_HORIZONTAL);
        Glide.with(context).load(list.get(position).getDataInfoImg()).into(viewHolder.image);
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "선택 : "+position+",날짜 : "+list.get(position).getDataDate(), Toast.LENGTH_SHORT).show();
                popupXml(list.get(position).getDataInfoImg(),list.get(position).getDataDate());
            }
        });
        return convertView;
    }
    private class ImageDownload extends AsyncTask<String, Void, Void> {

        private String fileName;
        private final String SAVE_FOLDER = "/save_securi";

        protected Void doInBackground(String... params) {

            //다운로드 경로를 지정
            String savePath = Environment.getExternalStorageDirectory().toString() + SAVE_FOLDER;

            File dir = new File(savePath);

            //상위 디렉토리가 존재하지 않을 경우 생성
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Date day = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA);
            fileName = String.valueOf(sdf.format(day));

            //웹 서버 쪽 파일이 있는 경로
            String fileUrl = params[0];

            //다운로드 폴더에 동일한 파일명이 존재하는지 확인
            if (new File(savePath + "/" + fileName).exists() == false) {
            } else {
            }

            String localPath = savePath + "/" + fileName + ".jpg";

            try {
                URL imgUrl = new URL(fileUrl);
                //서버와 접속하는 클라이언트 객체 생성
                HttpURLConnection conn = (HttpURLConnection)imgUrl.openConnection();
                int len = conn.getContentLength();
                byte[] tmpByte = new byte[len];
                //입력 스트림을 구한다
                InputStream is = conn.getInputStream();
                File file = new File(localPath);
                //파일 저장 스트림 생성
                FileOutputStream fos = new FileOutputStream(file);
                int read;
                //입력 스트림을 파일로 저장
                for (;;) {
                    read = is.read(tmpByte);
                    if (read <= 0) {
                        break;
                    }
                    fos.write(tmpByte, 0, read); //file 생성
                }
                is.close();
                fos.close();
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
    public class InfoViewHolder{
        public ImageView image;
        public TextView date;
    }
    //팝업 다이얼로그 구현부
    public void popupXml(String resId, String date){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.securi_datainfo_dialog,null);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(date);
        Glide.with(context).load(resId).into(imageView);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("상세보기").setView(view);
        builder.setNegativeButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog!=null){
                    dialog.dismiss();
                }
            }
            
        });
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog!=null){
                    dialog.dismiss();
                }
            }
        });
        dialog = builder.create();
        dialog.setOnShowListener( new DialogInterface.OnShowListener() {
            @Override public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            }
        });
        dialog.show();
    }

}
