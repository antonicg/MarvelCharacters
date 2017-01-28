package com.antonicastejon.marvelcharacters.di;

import com.antonicastejon.marvelcharacters.BuildConfig;
import com.antonicastejon.marvelcharacters.net.services.MarvelApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

@Module
public class NetModule {

    @Provides
    MarvelApi providesMarvelApi(Retrofit retrofit) {
        return retrofit.create(MarvelApi.class);
    }

    @Provides
    Retrofit providesRetrofit(CallAdapter.Factory callAdapterFactory, GsonConverterFactory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_BASE_URL)
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(converterFactory)
                .build();
    }

    @Provides
    CallAdapter.Factory providesCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    GsonConverterFactory providesGsonConverterFactory() {
        return GsonConverterFactory.create();
    }
}
