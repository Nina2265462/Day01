package com.baway.monthtest.tab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.monthtest.R;
import com.baway.monthtest.WebActivity;
import com.bumptech.glide.Glide;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Tab02 extends Fragment {

    private ImageView img;
    private TextView tv01;
    private TextView tv02;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab02_layout, container, false);
        //找控件
        img = view.findViewById(R.id.img);
        tv01 = view.findViewById(R.id.tv01);
        tv02 = view.findViewById(R.id.tv02);

        //设置头像的点击事件
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });
        //第一个点击事件
        tv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("key", "file:///android_asset/w.html");
                startActivity(intent);
            }
        });
        tv02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "2019-05-07", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        Glide.with(getActivity()).load(uri).into(img);
    }
}
