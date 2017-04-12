package com.antonicastejon.model.local;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
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

    public T findData(final Class<T> fromDataClass, long id) {
        Realm realm = Realm.getDefaultInstance();
        T finded = realm.where(fromDataClass).equalTo(PersistanceFields.ID, id).findFirst();
        realm.close();
        return finded;
    }

    public List<T> findAllSync(Realm realm, final Class<T> fromDataClass, Long[] in) {
        RealmResults<T> resultsFromRealm = realm.where(fromDataClass)
                .in(PersistanceFields.ID, in)
                .findAll();

        return resultsFromRealm.subList(0, resultsFromRealm.size());
    }

    public void saveAsync(T object) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(r -> r.copyToRealm(object));
        }
        finally {
            if (realm != null) realm.close();
        }
    }

    public void deleteAsync(final Class<T> fromDataClass, long idToDelete) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(r -> {
                T toDelete = r.where(fromDataClass)
                        .equalTo(PersistanceFields.ID, idToDelete)
                        .findFirst();
                if (toDelete != null) toDelete.deleteFromRealm();
            });
        }
        finally {
            if (realm != null) realm.close();
        }
    }
}
