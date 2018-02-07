package com.tikal.realm;


import android.content.Context;

import org.androidannotations.annotations.EBean;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * creates all our repository
 */

@EBean(scope = EBean.Scope.Singleton)
public class ReposModule {

    private final AppRepo mAppRepo;

    public ReposModule(Context context) {
        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        this.mAppRepo = new AppRepo(realmConfiguration);
    }

    public AppRepo appRepo() {
        return mAppRepo;
    }
}
