package com.baway.monthtest.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.baway.monthtest.R;
import com.baway.monthtest.adapter.MyAdapter;
import com.baway.monthtest.bean.Bean;
import com.baway.monthtest.http.HttpUntil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Tab01 extends Fragment {

    private PullToRefreshListView plv;
    private HttpUntil until;
    private String str = "http://172.17.8.100/movieApi/movie/v1/findHotMovieList?count=3&page=";
    private int page = 1;
    private ArrayList<Bean> list;
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab01_layout, container, false);
        //找控件
        plv = view.findViewById(R.id.plv);
        //调用方法
        until = HttpUntil.getHttpUntil();
        //判断是否有网络
        if (until.isNetWork(getContext())) {
            Toast.makeText(getContext(), "有网络", Toast.LENGTH_LONG).show();
        }
        //开始解析
        getData();
        list = new ArrayList<>();
        adapter = new MyAdapter(list, getContext());
        plv.setAdapter(adapter);
        plv.setScrollingWhileRefreshingEnabled(true);
        plv.setMode(PullToRefreshBase.Mode.BOTH);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                getData();
            }
        });
        return view;
    }

    private void getData() {
        until.AsyncTask(str + page, new HttpUntil.CallBackPostExecute() {
            @Override
            public void getPostExecute(String s) {
                try {
                    ArrayList<Bean> arr = new ArrayList<>();
                    JSONObject object = new JSONObject(s);
                    JSONArray result = object.getJSONArray("result");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject obj = (JSONObject) result.get(i);
                        String name = obj.getString("name");
                        String imageUrl = obj.getString("imageUrl");
                        arr.add(new Bean(name, imageUrl));
                    }
                    //判断是否为第一页
                    if (page == 1) {
                        list.clear();
                    }
                    //添加list集合
                    list.addAll(arr);
                    //刷新适配器
                    adapter.notifyDataSetChanged();
                    //完成刷新
                    plv.onRefreshComplete();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
