package com.antonicastejon.model.repository.api;

import com.antonicastejon.model.repository.entities.CharacterRepository;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Antoni Castejón on 27/01/2017.
 */

public interface MarvelApi {

    @GET("characters")
    Flowable<ResponseWrapper<CharacterRepository>> getCharacters(@Query("ts") long timeStamp,
                                                                 @Query("apikey") String apiKey,
                                                                 @Query("hash") String hash,
                                                                 @Query("limit") int limit,
                                                                 @Query("offset") int offset);
}
