package com.tikal.realm;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;


/**
 * Created by Elad
 */
public abstract class BaseRealmRepo {

    protected final RealmConfiguration mRealmConfiguration;

    public BaseRealmRepo(RealmConfiguration realmConfiguration) {
        mRealmConfiguration = realmConfiguration;
    }

    protected Realm getRealm() {
        return Realm.getInstance(mRealmConfiguration);
    }

    /**
     * will return false only if an exception has been thrown
     *
     * @param realm
     * @param command
     * @return
     */
    protected boolean applyTransaction(Realm realm, TransactionCommand command) {
        realm.beginTransaction();

        try {
            command.apply();
            return true;
        } catch (Exception exception) {
            realm.cancelTransaction();
            return false;
        } finally {
            realm.commitTransaction();
        }
    }

    protected boolean applyAddTransaction(Realm realm, TransactionCommand command) {
        boolean success = false;
        try {
            realm.beginTransaction();
            command.apply();
            success = true;
            realm.commitTransaction();
        } catch (RealmPrimaryKeyConstraintException e) {
            realm.cancelTransaction();
            success = true;
        } catch (Exception exception) {
            realm.cancelTransaction();
        }

        return success;
    }


    public interface TransactionCommand {
        void apply();
    }
}
