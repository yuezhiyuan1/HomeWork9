package com.example.lenovo.homework9;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.lenovo.homework9.Model.bean.IndexBean;
import com.example.lenovo.homework9.base.BaseActivity;
import com.example.lenovo.homework9.intc.news.NewsContract;
import com.example.lenovo.homework9.presenter.MyPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NewsContract.View {


    @BindView(R.id.txt1)
    TextView mTxt1;
    @BindView(R.id.txt2)
    TextView mTxt2;

    @Override
    public void OnSuccess() {

    }

    @Override
    public void OnFiule(String st) {
        Log.i("tag", "OnFiule: " + st);
    }

    @Override
    protected MyPresenter initMvpPresnter() {
        return new MyPresenter();
    }

    @Override
    protected void initData() {
        ((MyPresenter) presenter).getIndex();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void getIndexReturn(IndexBean indexBean) {

    }
}
