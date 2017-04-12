package com.antonicastejon.marvelcharacters;

import android.app.Application;

import com.antonicastejon.model.local.Persistance;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class MarvelCharactersApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Persistance.InitializeDb(this);
    }
}
