package com.example.lenovo.homework9.intc.news;

import com.example.lenovo.homework9.Model.bean.VersionBean;
import com.example.lenovo.homework9.intc.IBaseView;
import com.example.lenovo.homework9.intc.IPresenter;

public interface SplashContract {

    interface View extends IBaseView {

        void versionReturn(VersionBean versionBean);

    }

    interface Persenter extends IPresenter<View> {
        void getVersion(String pg, String name);
    }

}
