package com.antonicastejon.model.repository.api;

import android.content.Context;

import com.antonicastejon.model.BuildConfig;
import com.antonicastejon.model.R;
import com.antonicastejon.model.repository.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

@Module
public class NetModule {

    private static final int CONNECT_TIMEOUT = 10;
    private static final int READ_TIMEOUT = 10;

    @Provides
    @Named(Constants.NAMED_PUBLIC_KEY)
    String providesPublicKey(Context context) {
        return context.getResources().getString(R.string.public_key);
    }

    @Provides
    @Named(Constants.NAMED_PRIVATE_KEY)
    String providesPrivateKey(Context context) {
        return context.getResources().getString(R.string.private_key);
    }

    @Provides
    MarvelApi providesMarvelApi(Retrofit retrofit) {
        return retrofit.create(MarvelApi.class);
    }

    @Provides
    Retrofit providesRetrofit(OkHttpClient client, CallAdapter.Factory callAdapterFactory, GsonConverterFactory converterFactory) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.SERVER_BASE_URL)
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(converterFactory)
                .build();
    }

    @Provides
    OkHttpClient providesOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
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
