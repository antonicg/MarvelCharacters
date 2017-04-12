package com.antonicastejon.model.local.entities;

import io.realm.RealmObject;

/**
 * Created by Antoni Castejón on 11/04/2017.
 */

public class FavoriteCharacter extends RealmObject {

    private long id;

    public FavoriteCharacter() {
        id = Long.MIN_VALUE;
    }

    public FavoriteCharacter(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
