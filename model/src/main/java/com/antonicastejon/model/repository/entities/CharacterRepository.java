package com.antonicastejon.model.repository.entities;

import android.os.Parcel;

import java.util.List;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class CharacterRepository {
    private int id;
    private String name;
    private String description;
    private int pageCount;
    private ImageRepository thumbnail;

    protected CharacterRepository(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        pageCount = in.readInt();
        thumbnail = in.readParcelable(ImageRepository.class.getClassLoader());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public ImageRepository getThumbnail() {
        return thumbnail;
    }

}
