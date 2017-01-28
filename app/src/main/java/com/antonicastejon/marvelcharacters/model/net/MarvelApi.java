package com.antonicastejon.marvelcharacters.model.net;

import com.antonicastejon.marvelcharacters.model.net.model.ComicDataWrapper;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Antoni Castejón on 27/01/2017.
 */

public interface MarvelApi {

    @GET("characters/{characterId}/comics")
    Flowable<ComicDataWrapper> getComicsFromCharacter(@Path("characterId") long characterId,
                                                      @Query("ts") long timeStamp,
                                                      @Query("apikey") String apiKey,
                                                      @Query("hash") String hash);
}
