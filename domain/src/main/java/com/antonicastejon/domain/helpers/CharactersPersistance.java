package com.antonicastejon.domain.helpers;

import com.antonicastejon.domain.business.entities.Character;
import com.antonicastejon.model.local.Persistance;
import com.antonicastejon.model.local.entities.FavoriteCharacter;

import java.util.List;

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
        if (character.isFavorite()) persistance.saveAsync(new FavoriteCharacter(character.getId()));
        else persistance.deleteAsync(FavoriteCharacter.class, character.getId());
    }

    public void setFavoriteStateIn(List<Character> businessCharacters) {
        Long[] ids = constructIdArrayFrom(businessCharacters);

        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            List<FavoriteCharacter> allInIds = persistance.findAllSync(realm, FavoriteCharacter.class, ids);
            for (FavoriteCharacter favoriteCharacter : allInIds) {
                for (Character businessCharacter : businessCharacters) {
                    if (favoriteCharacter.getId() == businessCharacter.getId()) {
                        businessCharacter.setFavorite(true);
                        break;
                    }
                }
            }
        } finally {
            if (realm != null) realm.close();
        }
    }

    private Long[] constructIdArrayFrom(List<Character> businessCharacters) {
        int size = businessCharacters.size();
        Long[] ids = new Long[size];
        for (int pos = 0; pos < size; pos++) {
            ids[pos] = businessCharacters.get(pos).getId();
        }
        return ids;
    }
}
