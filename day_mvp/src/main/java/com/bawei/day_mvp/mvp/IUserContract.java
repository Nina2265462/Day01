package com.bawei.day_mvp.mvp;

import android.content.Context;

import com.bawei.day_mvp.bean.User;
import com.bawei.day_mvp.net.NetCallback;

import java.util.Map;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public interface IUserContract {
    /**
     * 用户 V 接口
     */
    public interface IUserView {
        //注册成功
        void registSuccess(String result);

        //注册失败
        void registFail();

        //登录成功
        void loginSuccess(String result);

        //登录失败
        void loginFail();
    }

    /**
     * 用户 M 接口
     */
    public interface IUserModel {
        //网络交互 HTTP
        void doHttpPost(String url, Map<String,String> param, NetCallback callback);
    }

    /**
     * 用户 P 接口
     * 业务:
     * 1.注册
     * 2.登录
     */
    public interface IUserPresenter {
        //绑定view
        void attach(IUserView view);

        //解绑 释放内存
        void detach();

        //注册业务逻辑
        void regist(User user);

        //登录业务逻辑
        void login(User user);

        /**
         * 把电话和密码 封装成user
         */
        User inputToUser(String phone, String pwd);

        /**
         * 跳转到主界面
         */
        void toMain(Context context);
    }
}
