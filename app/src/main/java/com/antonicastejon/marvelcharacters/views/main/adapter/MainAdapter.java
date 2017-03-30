package com.antonicastejon.marvelcharacters.views.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antonicastejon.domain.business.entities.Comic;
import com.antonicastejon.marvelcharacters.R;
import com.antonicastejon.marvelcharacters.utils.image.Images;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ComicViewHolder> {

    public interface ItemPressedListener {
        void onComicPressed(Comic comic, View transitionView);
    }
    private final List<Comic> data;
    private final ItemPressedListener itemPressedListener;
    private final Images images;

    public MainAdapter(ItemPressedListener itemPressedListener, Images images) {
        this.data = new ArrayList<>();
        this.itemPressedListener = itemPressedListener;
        this.images = images;
    }

    public List<Comic> getData() {
        return data;
    }

    public void update(@NonNull List<Comic> data) {
        this.data.addAll(data);

        int dataSize = this.data.size();
        int startRange = dataSize - data.size();
        int endRange = dataSize;
        notifyItemRangeInserted(startRange, endRange);
    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_comic, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComicViewHolder holder, int position) {
        Comic comic = data.get(position);
        String imageUrl = comic.getThumbnailUrl();
        if (imageUrl != null) images.loadForCell(imageUrl, holder.imageView);
        holder.textView.setText(comic.getTitle());
        holder.itemView.setOnClickListener(view -> itemPressedListener.onComicPressed(comic, holder.imageView));
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

        View itemView;

        public ComicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
