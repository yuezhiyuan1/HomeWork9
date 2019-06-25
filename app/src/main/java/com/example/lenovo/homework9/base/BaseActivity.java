package com.example.lenovo.homework9.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.homework9.R;
import com.example.lenovo.homework9.intc.IBaseView;
import com.example.lenovo.homework9.intc.IPresenter;
import com.example.lenovo.homework9.utils.SystemUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Lenovo on 2019/6/4.
 */

public abstract class BaseActivity<V extends IBaseView,P extends IPresenter> extends AppCompatActivity implements IBaseView {

    protected P presenter;
    private Unbinder mBind;
    protected Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mBind = ButterKnife.bind(this);
        if(!SystemUtils.checkNetWork()){
            //自定义布局实现无网络状态的提示
            View view = LayoutInflater.from(this).inflate(R.layout.layout_network_error,null);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            addContentView(view,params);
        }else{
            context = this;
            initView();
            presenter = initMvpPresnter();
            if(presenter != null){
                presenter.atachView(this);
            }
            initData();
        }
    }

    protected abstract P initMvpPresnter();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayout();

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter != null){
            presenter.atachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBind != null){
            mBind.unbind();
        }
        if(presenter != null){
            presenter.detachView();
        }
    }
}
