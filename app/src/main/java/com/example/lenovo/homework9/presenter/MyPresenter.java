package com.example.lenovo.homework9.presenter;

import com.example.lenovo.homework9.Model.bean.IndexBean;
import com.example.lenovo.homework9.Model.http.HttpManager;
import com.example.lenovo.homework9.base.BasePresenter;
import com.example.lenovo.homework9.commonSubscriber.CommonSubscriber;
import com.example.lenovo.homework9.intc.news.NewsContract;
import com.example.lenovo.homework9.utils.RxUtils;

/**
 * Created by Lenovo on 2019/6/4.
 */

public class MyPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {
    @Override
    public void getIndex() {
        addSubscribe(HttpManager.getMyApi().getIndexData()
                .compose(RxUtils.<IndexBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<IndexBean>(mView){
                    @Override
                    public void onNext(IndexBean indexBean) {
                        mView.getIndexReturn(indexBean);
                    }
                }));
    }
}
