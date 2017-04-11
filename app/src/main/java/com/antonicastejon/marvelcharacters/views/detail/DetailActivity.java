package com.antonicastejon.marvelcharacters.views.detail;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.antonicastejon.domain.business.entities.Character;
import com.antonicastejon.marvelcharacters.R;
import com.antonicastejon.marvelcharacters.di.DaggerDetailComponent;
import com.antonicastejon.marvelcharacters.di.DetailPresenterModule;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.base.BaseMvpActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class DetailActivity extends BaseMvpActivity implements DetailView {

    public final static String KEY_EXTRA_CHARACTER = "character";
    private final static String CHARACTER_EXTRA = "character";

    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.text_title)
    TextView textViewTitle;
    @BindView(R.id.text_description)
    TextView textViewDescription;
    @BindView(R.id.text_pages)
    TextView textViewPages;
    @BindView(R.id.collapsing_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @Inject
    DetailPresenter presenter;

    public static Intent getIntent(AppCompatActivity activity, Character character) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(CHARACTER_EXTRA, character);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        injectDependencies();

        initCollapsingToolbarLayout();
        showCharacterFromExtras();
    }

    private void injectDependencies() {
        ButterKnife.bind(this);

        DaggerDetailComponent.builder()
                .detailPresenterModule(new DetailPresenterModule(this))
                .build()
                .inject(this);
    }

    private void initCollapsingToolbarLayout() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }

        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
    }


    private void showCharacterFromExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Character character = extras.getParcelable(CHARACTER_EXTRA);
            if (character != null) presenter.show(character);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.fav);
        Character character = presenter.getCharacter();
        item.setIcon(character != null && character.isFavorite() ? R.drawable.ic_favorite_black_24dp : R.drawable.ic_favorite_border_black_24dp);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.fav:
                onFavPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        int result = presenter.isFavoriteStateChanged() ? RESULT_OK : RESULT_CANCELED;
        if (presenter.isFavoriteStateChanged()) {
            Intent data = new Intent();
            data.putExtra(KEY_EXTRA_CHARACTER, presenter.getCharacter());
            setResult(RESULT_OK, data);
        }
        else setResult(RESULT_CANCELED);
        finish();
    }

    private void onFavPressed() {
        presenter.markCharacterAsFavorite();
    }

    @Override
    public void showTitle(String title) {
        collapsingToolbarLayout.setTitle(title);
        textViewTitle.setText(title);
    }

    @Override
    public void showDescription(String description) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textViewDescription.setText(Html.fromHtml(description, 0));
        }
        else {
            textViewDescription.setText(Html.fromHtml(description));
        }
    }

    @Override
    public void showImage(Images images, String urlImage) {
        images.loadForDetail(urlImage, imageView);
    }

    @Override
    public void showIsFavorite() {
        invalidateOptionsMenu();
    }
}
