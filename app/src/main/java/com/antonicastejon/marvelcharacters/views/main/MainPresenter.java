package com.antonicastejon.marvelcharacters.views.main;

import android.util.Log;

import com.antonicastejon.marvelcharacters.model.Comic;
import com.antonicastejon.marvelcharacters.net.requests.base.RequestConsumer;
import com.antonicastejon.marvelcharacters.net.requests.comics.ComicsRequest;
import com.antonicastejon.marvelcharacters.net.response.ResponseWrapper;
import com.antonicastejon.marvelcharacters.net.services.MarvelService;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class MainPresenter extends BasePresenter<MainView> implements RequestConsumer.Callback<Comic> {

    private final static String TAG = MainPresenter.class.getName();

    private final ComicsRequest comicsRequest;
    private final List<Comic> comicList;
    private final Images images;

    private boolean isInitialized;
    private int totalItems;

    public MainPresenter(MainView mainView, MarvelService marvelService, Images images) {
        super(mainView);
        this.images = images;
        this.comicList = new ArrayList<>();
        RequestConsumer<Comic> requestConsumer = new RequestConsumer<>(this);
        comicsRequest = new ComicsRequest(marvelService, requestConsumer);
    }

    void load(int offset) {
        if (!isInitialized) {
            Log.e(TAG, "You must call presenter.init() first");
            return;
        }
        if (totalItemsAreNotShowingYet(offset)) {

            getView().showLoadingAlert();

            Log.d(TAG, "loading more items..." + offset);
            comicsRequest.execute(offset);
        }
    }

    private boolean totalItemsAreNotShowingYet(int currentItems) {
        return currentItems == 0 || currentItems < totalItems;
    }

    @Override
    public void onResponse(ResponseWrapper.DataContainer<Comic> data) {
        totalItems = data.getTotal();

        List<Comic> results = data.getResults();

        comicList.addAll(results);

        MainView view = getView();
        view.dismisssLoadingAlert();
        view.updateComics();
    }

    @Override
    public void onError(int code, String message) {
        MainView view = getView();
        view.errorLoadingComics();
        view.dismisssLoadingAlert();
    }


    public void init() {
        isInitialized = true;
        getView().initializeComicsView(images, comicList);
    }
}
