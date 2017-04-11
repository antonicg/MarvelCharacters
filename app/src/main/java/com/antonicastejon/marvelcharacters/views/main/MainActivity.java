package com.antonicastejon.marvelcharacters.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import com.antonicastejon.domain.business.entities.Character;
import com.antonicastejon.marvelcharacters.R;
import com.antonicastejon.marvelcharacters.di.ApplicationModule;
import com.antonicastejon.marvelcharacters.di.DaggerMainComponent;
import com.antonicastejon.marvelcharacters.di.MainPresenterModule;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.utils.listeners.EndlessScrollListener;
import com.antonicastejon.marvelcharacters.views.base.BaseMvpActivity;
import com.antonicastejon.marvelcharacters.views.detail.DetailActivity;
import com.antonicastejon.marvelcharacters.views.main.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseMvpActivity implements MainView, MainAdapter.ItemPressedListener {

    private final static String KEY_BUNDLE_CHARACTERS = "characters";
    private final static String KEY_BUNDLE_CURRENT_PAGE = "page";

    private final static int START_OFFSET = 0;
    private final static int GRID_SPAN_PORTRAIT = 2;
    private final static int GRID_SPAN_LANDSCAPE = 3;

    @Inject MainPresenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.stub_error_loading) ViewStub viewStubErrorLoading;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private Button buttonRetry;

    private MainAdapter adapter;
    private EndlessScrollListener endlessScrollListener;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        injectDependencies();

        initializeToolbar();

        if (savedInstanceState != null) {
            currentPage = savedInstanceState.getInt(KEY_BUNDLE_CURRENT_PAGE);
            List<Character> characters = savedInstanceState.getParcelableArrayList(KEY_BUNDLE_CHARACTERS);
            if (characters != null) presenter.initWith(characters);
            else initializePresenterAndLoad();
        }
        else {
            initializePresenterAndLoad();
        }
    }

    private void initializePresenterAndLoad() {
        presenter.init();
        load(START_OFFSET);
    }

    private void injectDependencies() {
        ButterKnife.bind(this);

        DaggerMainComponent.builder()
                .applicationModule(new ApplicationModule(getApplication()))
                .mainPresenterModule(new MainPresenterModule(this))
                .build()
                .inject(this);
    }

    private void initializeToolbar() {
        if (toolbar == null) return;

        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(toolbar.getTitle());
        }
    }

    private void load(int offset) {
        presenter.load(offset);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (adapter != null) {
            outState.putParcelableArrayList(KEY_BUNDLE_CHARACTERS, (ArrayList<? extends Parcelable>) adapter.getData());
        }
        outState.putInt(KEY_BUNDLE_CURRENT_PAGE, endlessScrollListener.getCurrentPage());
    }

    @Override
    public void initializeCharactersView(Images images) {
        initializeCharactersViewWith(images, null);
    }

    @Override
    public void initializeCharactersViewWith(Images images, @Nullable List<Character> characters) {
        if (recyclerView == null || presenter == null) return;

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(getGridSpan(), StaggeredGridLayoutManager.VERTICAL);

        endlessScrollListener = new EndlessScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.load(totalItemsCount);
            }
        };
        endlessScrollListener.setCurrentPage(currentPage);
        recyclerView.addOnScrollListener(endlessScrollListener);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new MainAdapter(this, images);
        recyclerView.setAdapter(adapter);

        if (characters != null) adapter.update(characters);
    }

    private int getGridSpan() {
        return isLandscape() ? GRID_SPAN_LANDSCAPE : GRID_SPAN_PORTRAIT;
    }

    @Override
    public boolean thereAreAnyCharacter() {
        return adapter != null && adapter.getItemCount() > 0;
    }

    @Override
    public void updateCharacters(List<Character> viewData) {
        if (adapter != null) adapter.update(viewData);
    }

    @Override
    public void errorLoadingCharacters() {
        if (endlessScrollListener != null) endlessScrollListener.resetState();
    }

    @Override
    public void showRetryMessage() {
        viewStubErrorLoading.setVisibility(View.VISIBLE);
        buttonRetry = (Button) findViewById(R.id.button_retry);
        if (buttonRetry != null) {
            buttonRetry.setOnClickListener(v -> {
                load(START_OFFSET);
            });
        }
    }

    @Override
    public void hideRetryMessage() {
        viewStubErrorLoading.setVisibility(View.GONE);
    }

    @Override
    public boolean isShowingRetryMessage() {
        return viewStubErrorLoading.getVisibility() == View.VISIBLE;
    }

    @Override
    public void onCharacterPressed(Character character, View transitionView) {
        Intent intent = DetailActivity.getIntent(this, character);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, transitionView, getString(R.string.transition_detail_image));
        startActivity(intent, options.toBundle());

    }
}
