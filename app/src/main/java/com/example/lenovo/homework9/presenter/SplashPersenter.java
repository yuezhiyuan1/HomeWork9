package com.example.lenovo.homework9.presenter;

import com.example.lenovo.homework9.Model.bean.VersionBean;
import com.example.lenovo.homework9.Model.http.HttpManager;
import com.example.lenovo.homework9.base.BasePresenter;
import com.example.lenovo.homework9.commonSubscriber.CommonSubscriber;
import com.example.lenovo.homework9.intc.news.SplashContract;
import com.example.lenovo.homework9.utils.RxUtils;

public class SplashPersenter extends BasePresenter<SplashContract.View> implements SplashContract.Persenter {

    @Override
    public void getVersion(String pg, String name) {
        addSubscribe(HttpManager.getMyApi().getVersionData(pg,name)
                .compose(RxUtils.<VersionBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<VersionBean>(mView){
                    @Override
                    public void onNext(VersionBean versionBean) {
                        mView.versionReturn(versionBean);
                    }
                }));
    }
}
