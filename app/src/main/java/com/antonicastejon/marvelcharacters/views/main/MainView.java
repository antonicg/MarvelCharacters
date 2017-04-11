package com.antonicastejon.marvelcharacters.views.main;

import com.antonicastejon.domain.business.entities.Character;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.base.BaseMvpView;

import java.util.List;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public interface MainView extends BaseMvpView {

    void initializeCharactersView(Images images);

    void initializeCharactersViewWith(Images images, List<Character> characters);

    boolean thereAreAnyCharacter();

    void updateCharacters(List<Character> viewData);

    void errorLoadingCharacters();

    void showRetryMessage();

    void hideRetryMessage();

    boolean isShowingRetryMessage();

    void refreshItem(int pos);
}
