package com.antonicastejon.marvelcharacters.views.main;

import android.support.annotation.NonNull;
import android.util.Log;

import com.antonicastejon.domain.RequestConsumer;
import com.antonicastejon.domain.business.entities.Character;
import com.antonicastejon.domain.business.usecases.GetCharactersUseCase;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.base.BasePresenter;

import java.util.List;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class MainPresenter extends BasePresenter<MainView> implements RequestConsumer.Callback<List<Character>> {

    private final static String TAG = MainPresenter.class.getName();

    private final GetCharactersUseCase getCharactersUseCase;
    private final RequestConsumer<List<Character>> requestConsumer;
    private final Images images;

    private boolean isInitialized;
    private int totalItems;

    public MainPresenter(MainView mainView, GetCharactersUseCase getCharactersUseCase, Images images) {
        super(mainView);
        this.images = images;

        requestConsumer = new RequestConsumer<>(this);
        this.getCharactersUseCase = getCharactersUseCase;
    }

    void load(int offset) {
        if (!isInitialized) {
            Log.e(TAG, "You must call presenter.init() first");
            return;
        }
        if (totalItemsAreNotShowingYet(offset)) {

            getView().showLoadingAlert();

            Log.d(TAG, "loading more items..." + offset);
            getCharactersUseCase.setOffset(offset);
            getCharactersUseCase.execute(requestConsumer);
        }
    }

    private boolean totalItemsAreNotShowingYet(int currentItems) {
        return currentItems == 0 || totalItems == 0 || currentItems < totalItems;
    }

    @Override
    public void onResponse(List<Character> data) {
        totalItems = getCharactersUseCase != null ? getCharactersUseCase.getTotalItems() : 0;

        MainView view = getView();
        if (view.isShowingRetryMessage()) view.hideRetryMessage();
        view.dismisssLoadingAlert();
        view.updateCharacters(data);
    }

    @Override
    public void onError(int code, String message) {
        MainView view = getView();
        view.errorLoadingCharacters();
        view.dismisssLoadingAlert();

        if (showRetryMessage()) {
            if (!view.isShowingRetryMessage()) view.showRetryMessage();
        }
    }

    private boolean showRetryMessage() {
        return !getView().thereAreAnyCharacter();
    }


    void init() {
        isInitialized = true;
        getView().initializeCharactersView(images);
    }

    void initWith(@NonNull List<Character> characters) {
        isInitialized = true;
        getView().initializeCharactersViewWith(images, characters);
    }
}
