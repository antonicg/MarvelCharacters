package com.antonicastejon.marvelcharacters.views.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.antonicastejon.marvelcharacters.R;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class BaseMvpActivity extends AppCompatActivity implements BaseMvpView {

    private ProgressDialog progressDialog;

    @Override
    public void showLoadingAlert() {
        if (progressDialog == null) initializeProgressDialog();
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();
    }

    private void initializeProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(Boolean.FALSE);
    }

    @Override
    public void dismisssLoadingAlert() {
        if (progressDialog != null) progressDialog.dismiss();
    }

    protected boolean isLandscape() {
        return getResources().getBoolean(R.bool.lanscape);
    }
}
