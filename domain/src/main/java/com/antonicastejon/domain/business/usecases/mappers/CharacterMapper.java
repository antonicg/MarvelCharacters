package com.antonicastejon.domain.business.usecases.mappers;

import com.antonicastejon.domain.business.entities.Character;
import com.antonicastejon.domain.helpers.RepositoryHelper;
import com.antonicastejon.model.repository.api.ResponseWrapper;
import com.antonicastejon.model.repository.entities.CharacterRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by Antoni Castejón on 30/03/2017.
 */

public class CharacterMapper implements Function<ResponseWrapper<CharacterRepository>, List<Character>> {

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
                                characterRepository.getPageCount(),
                                RepositoryHelper.getThumbnailUrl(characterRepository)
                        );
                businessCharacters.add(businessCharacter);
            }
        }
        return businessCharacters;
    }
}
