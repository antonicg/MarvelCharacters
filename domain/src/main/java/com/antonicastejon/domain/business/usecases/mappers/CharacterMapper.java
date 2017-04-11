package com.antonicastejon.domain.business.usecases.mappers;

import com.antonicastejon.domain.business.entities.Character;
import com.antonicastejon.domain.helpers.RepositoryHelper;
import com.antonicastejon.model.local.Persistance;
import com.antonicastejon.model.local.entities.FavoriteCharacter;
import com.antonicastejon.model.repository.api.ResponseWrapper;
import com.antonicastejon.model.repository.entities.CharacterRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.realm.Realm;

/**
 * Created by Antoni Castejón on 30/03/2017.
 */

public class CharacterMapper implements Function<ResponseWrapper<CharacterRepository>, List<Character>> {

    @Inject Persistance<FavoriteCharacter> persistance;

    @Inject
    CharacterMapper() {}

    @Override
    public List<Character> apply(ResponseWrapper<CharacterRepository> characterResponseWrapper) throws Exception {
        List<Character> businessCharacters = new ArrayList<>();
        ResponseWrapper.DataContainer<CharacterRepository> data = characterResponseWrapper.getData();
        if (data != null) {
            List<CharacterRepository> results = data.getResults();
            for (CharacterRepository characterRepository : results) {
                Character businessCharacter =
                        new Character(
                                characterRepository.getId(),
                                characterRepository.getName(),
                                characterRepository.getDescription(),
                                RepositoryHelper.getThumbnailUrl(characterRepository)
                        );
                businessCharacters.add(businessCharacter);
            }
        }
        checkIfCharactersAreFavorites(businessCharacters);
        return businessCharacters;
    }

    private void checkIfCharactersAreFavorites(List<Character> businessCharacters) {
        Long[] ids = constructIdArrayFrom(businessCharacters);
        List<FavoriteCharacter> allInIds = persistance.findAllInIds(Realm.getDefaultInstance(), FavoriteCharacter.class, ids);
        for (FavoriteCharacter favoriteCharacter : allInIds) {
            for (Character businessCharacter : businessCharacters) {
                if (favoriteCharacter.getId() == businessCharacter.getId()) {
                    businessCharacter.setFavorite(true);
                    break;
                }
            }
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
