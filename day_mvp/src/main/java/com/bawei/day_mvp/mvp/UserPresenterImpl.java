package com.bawei.day_mvp.mvp;

import android.content.Context;
import android.content.Intent;

import com.bawei.day_mvp.Main2Activity;
import com.bawei.day_mvp.MainActivity;
import com.bawei.day_mvp.bean.User;
import com.bawei.day_mvp.net.NetCallback;

import java.util.HashMap;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class UserPresenterImpl implements IUserContract.IUserPresenter {

    private IUserContract.IUserView view1;
    private IUserContract.IUserModel model;

    @Override
    public void attach(IUserContract.IUserView view) {
        view1 = view;
        model = new UserModelImpl();
    }

    @Override
    public void detach() {
        if (view1 != null) {
            view1 = null;
        }
        if (model != null) {
            model = null;
        }
    }

    @Override
    public void regist(User user) {
        HashMap<String, String> param = new HashMap<>();
        param.put("phone", user.getPhone());
        param.put("pwd", user.getPwd());
        String url = "http://172.17.8.100/small/user/v1/register";
        model.doHttpPost(url, param, new NetCallback() {
            @Override
            public void onSuccess(String result) {
                view1.registSuccess(result);
            }

            @Override
            public void onFail(String msg) {
                view1.registFail();
            }
        });


    }

    @Override
    public void login(User user) {
        HashMap<String, String> param = new HashMap<>();
        param.put("phone", user.getPhone());
        param.put("pwd", user.getPwd());
        String url = "http://172.17.8.100/small/user/v1/login";
        model.doHttpPost(url, param, new NetCallback() {
            @Override
            public void onSuccess(String result) {
                view1.loginSuccess(result);
            }

            @Override
            public void onFail(String msg) {
                view1.loginFail();
            }
        });
    }

    @Override
    public User inputToUser(String phone, String pwd) {
        User user = new User(phone, pwd);
        return user;
    }

    @Override
    public void toMain(Context context) {
        context.startActivity(new Intent(context, Main2Activity.class));
    }
}
