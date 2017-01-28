package com.antonicastejon.marvelcharacters.views.main;

import android.util.Log;

import com.antonicastejon.marvelcharacters.model.Comic;
import com.antonicastejon.marvelcharacters.net.requests.base.RequestConsumer;
import com.antonicastejon.marvelcharacters.net.requests.comics.ComicsRequest;
import com.antonicastejon.marvelcharacters.net.response.ResponseWrapper;
import com.antonicastejon.marvelcharacters.net.services.MarvelService;
import com.antonicastejon.marvelcharacters.utils.image.Images;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class MainPresenter implements RequestConsumer.Callback<Comic> {

    private final static String TAG = MainPresenter.class.getName();

    private final MainView mainView;
    private final ComicsRequest comicsRequest;
    private final List<Comic> comicList;
    private final Images images;

    private boolean isInitialized;

    public MainPresenter(MainView mainView, MarvelService marvelService, Images images) {
        this.mainView = mainView;
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
        comicsRequest.execute(offset);
    }

    @Override
    public void onResponse(ResponseWrapper.DataContainer<Comic> data) {
        List<Comic> results = data.getResults();
        comicList.addAll(results);

        mainView.updateComics();
    }

    @Override
    public void onError(int code, String message) {
        Log.e(TAG, "Error: "  + code + " " + message);
    }

    public void init() {
        isInitialized = true;
        mainView.initializeView(images, comicList);
    }
}
