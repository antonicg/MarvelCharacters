package com.antonicastejon.model.local;

import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Antoni Castejón on 11/04/2017.
 */

public class Persistance<T extends RealmObject> {

    private static final String TAG = Persistance.class.getName();

    public static void InitializeDb(Context context) {
        Realm.init(context);
    }

    @Inject
    Persistance() {}

    public T findData(Realm realm, final Class<T> fromDataClass, long id) {
        return realm.where(fromDataClass).equalTo(PersistanceFields.ID, id).findFirst();
    }

    public List<T> findAllInIds(Realm realm, final Class<T> fromDataClass, Long[] in) {

        RealmResults<T> allItems = realm.where(fromDataClass)
                                            .in(PersistanceFields.ID, in)
                                            .findAll();

        return allItems.subList(0, allItems.size());
    }

    public void saveAsync(Realm realm, T object) {
        realm.executeTransaction(r -> r.copyToRealmOrUpdate(object));
    }

    public void deleteAsync(Realm realm, final Class<T> fromDataClass, long idToDelete) {
        realm.executeTransaction(r -> {
            T toDelete = r.where(fromDataClass)
                    .equalTo(PersistanceFields.ID, idToDelete)
                    .findFirst();
            toDelete.deleteFromRealm();
        });
    }
}
