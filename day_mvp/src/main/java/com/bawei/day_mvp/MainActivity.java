package com.bawei.day_mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.day_mvp.bean.User;
import com.bawei.day_mvp.mvp.IUserContract;
import com.bawei.day_mvp.mvp.UserPresenterImpl;

public class MainActivity extends AppCompatActivity implements IUserContract.IUserView {

    private EditText euser;
    private EditText epwd;
    private CheckBox cb;
    private UserPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        euser = findViewById(R.id.euser);
        epwd = findViewById(R.id.epwd);
        cb = findViewById(R.id.cb);
        presenter = new UserPresenterImpl();
        presenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    /**
     * 登录
     *
     * @param view
     */
    public void login(View view) {
        User user = presenter.inputToUser(euser.getText().toString(), epwd.getText().toString());
        presenter.login(user);
    }

    /**
     * 注册
     *
     * @param view
     */
    public void register(View view) {
        User user = presenter.inputToUser(euser.getText().toString(), epwd.getText().toString());
        presenter.regist(user);
    }

    @Override
    public void registSuccess(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registFail() {

    }

    @Override
    public void loginSuccess(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        presenter.toMain(this);
    }

    @Override
    public void loginFail() {

    }
}
