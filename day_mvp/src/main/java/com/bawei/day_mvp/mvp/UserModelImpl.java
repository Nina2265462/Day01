package com.bawei.day_mvp.mvp;

import com.bawei.day_mvp.http.HttpUtil;
import com.bawei.day_mvp.net.NetCallback;

import java.util.Map;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class UserModelImpl implements IUserContract.IUserModel {

    private final HttpUtil util;

    public UserModelImpl() {
        util = HttpUtil.getInstance();
    }

    @Override
    public void doHttpPost(String url, Map<String, String> param, NetCallback callback) {
        util.doHttpPost(url, param, callback);
    }
}
