package com.antonicastejon.marvelcharacters.views.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.antonicastejon.marvelcharacters.R;
import com.antonicastejon.marvelcharacters.di.DaggerMainComponent;
import com.antonicastejon.marvelcharacters.di.MainPresenterModule;
import com.antonicastejon.marvelcharacters.model.Comic;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.main.adapter.MainAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    private final static int START_OFFSET_COMICS = 0;
    private final static int GRID_SPAN = 2;

    @Inject MainPresenter presenter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private MainAdapter adapter;

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
    public void initializeView(Images images, List<Comic> viewData) {
        if (recyclerView == null || presenter == null) return;

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(GRID_SPAN, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new MainAdapter(viewData, images);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateComics() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
