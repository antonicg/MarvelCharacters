package com.antonicastejon.marvelcharacters.views.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antonicastejon.marvelcharacters.R;
import com.antonicastejon.marvelcharacters.model.Comic;
import com.antonicastejon.marvelcharacters.model.helpers.ComicHelper;
import com.antonicastejon.marvelcharacters.utils.image.Images;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ComicViewHolder> {

    private final List<Comic> data;

    private Images images;

    public MainAdapter(@NonNull List<Comic> data, Images images) {
        this.data = data;
        this.images = images;
    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_comic, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComicViewHolder holder, int position) {
        Comic comic = data.get(position);
        String imageUrl = ComicHelper.getThumbnailUrl(comic);
        if (imageUrl != null) images.load(imageUrl, holder.imageView);
        holder.textView.setText(comic.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ComicViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img)
        ImageView imageView;

        @BindView(R.id.text)
        TextView textView;

        public ComicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
