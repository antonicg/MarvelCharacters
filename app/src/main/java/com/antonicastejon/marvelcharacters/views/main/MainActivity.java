package com.antonicastejon.marvelcharacters.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.antonicastejon.marvelcharacters.R;
import com.antonicastejon.marvelcharacters.di.DaggerMainComponent;
import com.antonicastejon.marvelcharacters.di.MainPresenterModule;
import com.antonicastejon.marvelcharacters.model.Comic;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.utils.listeners.EndlessScrollListener;
import com.antonicastejon.marvelcharacters.views.base.BaseMvpActivity;
import com.antonicastejon.marvelcharacters.views.detail.DetailActivity;
import com.antonicastejon.marvelcharacters.views.main.adapter.MainAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseMvpActivity implements MainView, MainAdapter.ItemPressedListener {

    private final static int START_OFFSET_COMICS = 0;
    private final static int GRID_SPAN = 2;

    @Inject MainPresenter presenter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private MainAdapter adapter;
    private EndlessScrollListener endlessScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        injectDependencies();

        presenter.init();
        loadComics(START_OFFSET_COMICS);
    }



    private void injectDependencies() {
        ButterKnife.bind(this);

        DaggerMainComponent.builder()
                .mainPresenterModule(new MainPresenterModule(this))
                .build()
                .inject(this);
    }

    private void loadComics(int offset) {
        presenter.load(offset);
    }

    @Override
    public void initializeComicRecyclerView(Images images, List<Comic> viewData) {
        if (recyclerView == null || presenter == null) return;

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(GRID_SPAN, StaggeredGridLayoutManager.VERTICAL);

        endlessScrollListener = new EndlessScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.load(totalItemsCount);
            }
        };

        recyclerView.addOnScrollListener(endlessScrollListener);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new MainAdapter(viewData, this, images);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateComics() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void errorLoadingComics() {
        if (endlessScrollListener != null) endlessScrollListener.resetState();
    }

    @Override
    public void onComicPressed(Comic comic) {
        Intent intent = DetailActivity.getIntent(this, comic);
        startActivity(intent);
    }
}
