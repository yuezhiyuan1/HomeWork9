package com.example.lenovo.homework9.intc.news;

import com.example.lenovo.homework9.Model.bean.IndexBean;
import com.example.lenovo.homework9.intc.IBaseView;
import com.example.lenovo.homework9.intc.IPresenter;

public interface NewsContract {
    //主页数据
    interface View extends IBaseView {

        void getIndexReturn(IndexBean indexBean);
    }

    //新闻的Presenter层接口定义
    interface Presenter extends IPresenter<View> {

        void getIndex();
    }
}