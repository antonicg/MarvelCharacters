package com.antonicastejon.marvelcharacters.views.main;

import android.support.annotation.NonNull;
import android.util.Log;

import com.antonicastejon.domain.RequestConsumer;
import com.antonicastejon.domain.business.entities.Comic;
import com.antonicastejon.domain.business.usecases.GetComicsFromCharacterUseCase;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.base.BasePresenter;

import java.util.List;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class MainPresenter extends BasePresenter<MainView> implements RequestConsumer.Callback<List<Comic>> {

    private final static String TAG = MainPresenter.class.getName();

    private final GetComicsFromCharacterUseCase getComicsFromCharacterUseCase;
    private final RequestConsumer<List<Comic>> requestConsumer;
    private final Images images;

    private boolean isInitialized;
    private int totalItems;

    public MainPresenter(MainView mainView, GetComicsFromCharacterUseCase getComicsFromCharacterUseCase, Images images) {
        super(mainView);
        this.images = images;

        requestConsumer = new RequestConsumer<>(this);
        this.getComicsFromCharacterUseCase = getComicsFromCharacterUseCase;
    }

    void load(int offset) {
        if (!isInitialized) {
            Log.e(TAG, "You must call presenter.init() first");
            return;
        }
        if (totalItemsAreNotShowingYet(offset)) {

            getView().showLoadingAlert();

            Log.d(TAG, "loading more items..." + offset);
            getComicsFromCharacterUseCase.setOffset(offset);
            getComicsFromCharacterUseCase.execute(requestConsumer);
        }
    }

    private boolean totalItemsAreNotShowingYet(int currentItems) {
        return currentItems == 0 || totalItems == 0 || currentItems < totalItems;
    }

    @Override
    public void onResponse(List<Comic> data) {
        totalItems = data.size();

        MainView view = getView();
        if (view.isShowingRetryMessage()) view.hideRetryMessage();
        view.dismisssLoadingAlert();
        view.updateComics(data);
    }

    @Override
    public void onError(int code, String message) {
        MainView view = getView();
        view.errorLoadingComics();
        view.dismisssLoadingAlert();

        if (showRetryMessage()) {
            if (!view.isShowingRetryMessage()) view.showRetryMessage();
        }
    }

    private boolean showRetryMessage() {
        return !getView().thereAreAnyComic();
    }


    void init() {
        isInitialized = true;
        getView().initializeComicsView(images);
    }

    void initWith(@NonNull List<Comic> comics) {
        isInitialized = true;
        getView().initializeComicsViewWithComics(images, comics);
    }
}
