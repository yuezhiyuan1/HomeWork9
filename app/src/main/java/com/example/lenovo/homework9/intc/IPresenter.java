package com.example.lenovo.homework9.intc;

/**
 * Created by Lenovo on 2019/6/3.
 */

public interface IPresenter<V extends IBaseView> {

    void atachView(V view);

    void detachView();
}
