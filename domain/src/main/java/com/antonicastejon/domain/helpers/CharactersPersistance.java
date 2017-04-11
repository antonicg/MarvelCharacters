package com.antonicastejon.domain.helpers;

import com.antonicastejon.domain.business.entities.Character;
import com.antonicastejon.model.local.Persistance;
import com.antonicastejon.model.local.entities.FavoriteCharacter;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Antoni Castejón on 11/04/2017.
 */

public class CharactersPersistance {

    @Inject
    Persistance<FavoriteCharacter> persistance;

    @Inject
    CharactersPersistance() {}

    public void saveFavoriteState(Character character) {
        Realm realm = Realm.getDefaultInstance();
        if (character.isFavorite()) persistance.saveAsync(realm, new FavoriteCharacter(character.getId()));
        else persistance.deleteAsync(realm, FavoriteCharacter.class, character.getId());
    }
}
