package com.antonicastejon.marvelcharacters.views.splash;

import com.antonicastejon.marvelcharacters.views.base.BasePresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Antoni Castejón on 11/04/2017.
 */

public class SplashPresenter extends BasePresenter<SplashView> {

    private static final int SPLASH_TIME_SECONDS = 3;

    public SplashPresenter(SplashView mvpView) {
        super(mvpView);
    }

    public void startSplash() {
        Observable.timer(SPLASH_TIME_SECONDS, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(time -> getView().closeSplash());
    }
}
