package com.antonicastejon.domain.business.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Antoni Castejón on 30/03/2017.
 */

public class Comic implements Parcelable {
    private int id;
    private String title;
    private String description;
    private int pageCount;
    private String thumbnailUrl;
    private String randomImageUrl;

    public Comic(int id, String title, String description, int pageCount, String thumbnailUrl, String randomImageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.thumbnailUrl = thumbnailUrl;
        this.randomImageUrl = randomImageUrl;
    }

    protected Comic(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        pageCount = in.readInt();
        thumbnailUrl = in.readString();
        randomImageUrl = in.readString();
    }

    public static final Creator<Comic> CREATOR = new Creator<Comic>() {
        @Override
        public Comic createFromParcel(Parcel in) {
            return new Comic(in);
        }

        @Override
        public Comic[] newArray(int size) {
            return new Comic[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeInt(pageCount);
        parcel.writeString(thumbnailUrl);
        parcel.writeString(randomImageUrl);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getRandomImageUrl() {
        return randomImageUrl;
    }
}
