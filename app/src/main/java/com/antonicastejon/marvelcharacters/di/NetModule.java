package com.antonicastejon.marvelcharacters.di;

import com.antonicastejon.marvelcharacters.BuildConfig;
import com.antonicastejon.marvelcharacters.model.net.MarvelApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

@Module
public class NetModule {

    @Provides
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    MarvelApi providesMarvelApi(Retrofit retrofit) {
        return retrofit.create(MarvelApi.class);
    }
}
