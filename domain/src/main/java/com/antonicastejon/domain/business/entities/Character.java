package com.antonicastejon.domain.business.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Antoni Castejón on 30/03/2017.
 */

public class Character implements Parcelable {
    private long id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private boolean isFavorite;

    public Character(long id, String title, String description, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
    }

    protected Character(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        thumbnailUrl = in.readString();
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(thumbnailUrl);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
