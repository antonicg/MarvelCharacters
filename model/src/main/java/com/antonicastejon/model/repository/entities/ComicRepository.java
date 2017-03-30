package com.antonicastejon.model.repository.entities;

import android.os.Parcel;

import java.util.List;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class ComicRepository {
    private int id;
    private String title;
    private String description;
    private int pageCount;
    private ImageRepository thumbnail;
    private List<ImageRepository> images;

    protected ComicRepository(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        pageCount = in.readInt();
        thumbnail = in.readParcelable(ImageRepository.class.getClassLoader());
        images = in.createTypedArrayList(ImageRepository.CREATOR);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public ImageRepository getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ImageRepository thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<ImageRepository> getImageRepositories() {
        return images;
    }

    public void setImageRepositories(List<ImageRepository> imageRepositories) {
        this.images = imageRepositories;
    }
}
