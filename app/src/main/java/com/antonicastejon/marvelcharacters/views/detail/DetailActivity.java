package com.antonicastejon.marvelcharacters.views.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.antonicastejon.marvelcharacters.R;
import com.antonicastejon.marvelcharacters.di.DaggerDetailComponent;
import com.antonicastejon.marvelcharacters.di.DetailPresenterModule;
import com.antonicastejon.marvelcharacters.model.Comic;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.base.BaseMvpActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class DetailActivity extends BaseMvpActivity implements DetailView {

    private final static String COMIC_EXTRA = "comic";

    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.text_title)
    TextView textViewTitle;
    @BindView(R.id.text_description)
    TextView textViewDescription;
    @BindView(R.id.text_pages)
    TextView textViewPages;

    @Inject
    DetailPresenter presenter;

    public static Intent getIntent(AppCompatActivity activity, Comic comic) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(COMIC_EXTRA, comic);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        injectDependencies();

        showComicFromExtras();
    }

    private void injectDependencies() {
        ButterKnife.bind(this);

        DaggerDetailComponent.builder()
                .detailPresenterModule(new DetailPresenterModule(this))
                .build()
                .inject(this);
    }

    private void showComicFromExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Comic comic = extras.getParcelable(COMIC_EXTRA);
            if (comic != null) presenter.showComic(comic);
        }
    }

    @Override
    public void showTitle(String title) {
        textViewTitle.setText(title);
    }

    @Override
    public void showDescription(String description) {
        textViewDescription.setText(description);
    }

    @Override
    public void showImage(Images images, String urlImage) {
        images.load(urlImage, imageView);
    }

    @Override
    public void showPages(int pages) {
        textViewPages.setText(getResources().getQuantityString(R.plurals.pages, pages, pages));
    }
}
