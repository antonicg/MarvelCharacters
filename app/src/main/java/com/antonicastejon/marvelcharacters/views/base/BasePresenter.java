package com.antonicastejon.marvelcharacters.views.base;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public abstract class BasePresenter<T extends BaseMvpView> {

    private final T mvpView;

    protected BasePresenter(T mvpView) {
        this.mvpView = mvpView;
    }

    protected T getView() {
        return mvpView;
    }
}
