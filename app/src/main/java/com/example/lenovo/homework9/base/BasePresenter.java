package com.example.lenovo.homework9.base;

import com.example.lenovo.homework9.intc.IBaseView;
import com.example.lenovo.homework9.intc.IPresenter;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Lenovo on 2019/6/4.
 */

public class BasePresenter<V extends IBaseView> implements IPresenter<V> {

    protected V mView;
    private WeakReference<V> mWeakReference;
    protected CompositeDisposable mDisposable;


    @Override
    public void atachView(V view) {
        mWeakReference = new WeakReference<>(view);
        this.mView = mWeakReference.get();
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }


    private void unSubscribe() {
        if(mDisposable != null){
            mDisposable.clear();
        }
    }

    //添加观察者和被观察者的操作类
    //Disposable
    protected void addSubscribe(Disposable disposable){
        if(mDisposable == null)
            mDisposable = new CompositeDisposable();
        mDisposable.add(disposable);
    }
}
