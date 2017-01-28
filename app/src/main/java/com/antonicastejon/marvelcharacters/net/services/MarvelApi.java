package com.antonicastejon.marvelcharacters.net.services;

import com.antonicastejon.marvelcharacters.model.Comic;
import com.antonicastejon.marvelcharacters.net.response.ResponseWrapper;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Antoni Castejón on 27/01/2017.
 */

public interface MarvelApi {

    @GET("characters/{characterId}/comics")
    Flowable<ResponseWrapper<Comic>> getComicsFromCharacter(@Path("characterId") long characterId,
                                                            @Query("ts") long timeStamp,
                                                            @Query("apikey") String apiKey,
                                                            @Query("hash") String hash,
                                                            @Query("limit") int limit,
                                                            @Query("offset") int offset);
}
