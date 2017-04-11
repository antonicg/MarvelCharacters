package com.antonicastejon.model.repository.entities;

import android.os.Parcel;

import java.util.List;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class CharacterRepository {
    private long id;
    private String name;
    private String description;
    private int pageCount;
    private ImageRepository thumbnail;

    public long getId() {
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
