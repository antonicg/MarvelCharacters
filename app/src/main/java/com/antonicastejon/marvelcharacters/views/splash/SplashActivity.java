package com.antonicastejon.marvelcharacters.views.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.antonicastejon.marvelcharacters.R;
import com.antonicastejon.marvelcharacters.di.DaggerSplashComponent;
import com.antonicastejon.marvelcharacters.di.SplashPresenterModule;
import com.antonicastejon.marvelcharacters.views.base.BaseMvpActivity;
import com.antonicastejon.marvelcharacters.views.main.MainActivity;

import javax.inject.Inject;

/**
 * Created by Antoni Castejón on 11/04/2017.
 */

public class SplashActivity extends BaseMvpActivity implements SplashView {

    @Inject SplashPresenter splashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        injectDependencies();

        splashPresenter.startSplash();
    }

    private void injectDependencies() {
        DaggerSplashComponent.builder()
                .splashPresenterModule(new SplashPresenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void closeSplash() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
