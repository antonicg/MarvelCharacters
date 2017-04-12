package com.antonicastejon.marvelcharacters.views.detail;

import android.support.annotation.NonNull;

import com.antonicastejon.domain.business.entities.Character;
import com.antonicastejon.domain.helpers.CharactersPersistance;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.base.BasePresenter;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class DetailPresenter extends BasePresenter<DetailView> {

    private Images images;
    private Character character;
    private CharactersPersistance charactersPersistance;
    private boolean favoriteStateChanged = false;

    public DetailPresenter(DetailView mvpView, Images images, CharactersPersistance charactersPersistance) {
        super(mvpView);
        this.images = images;
        this.charactersPersistance = charactersPersistance;
    }

    public void show(@NonNull Character character) {
        this.character = character;

        DetailView view = getView();
        view.showTitle(character.getTitle());
        view.showDescription(character.getDescription());
        view.showImage(images, character.getThumbnailUrl());
        view.showIsFavorite();
    }

    public Character getCharacter() {
        return character;
    }

    public void markCharacterAsFavorite() {
        favoriteStateChanged = true;
        character.setFavorite(!character.isFavorite());
        charactersPersistance.saveFavoriteState(character);
        getView().showIsFavorite();
    }

    public boolean isFavoriteStateChanged() {
        return favoriteStateChanged;
    }
}
