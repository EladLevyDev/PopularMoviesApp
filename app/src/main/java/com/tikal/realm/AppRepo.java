package com.tikal.realm;

/**
 * Created by sacredoon on 03/01/2017.
 */

import com.tikal.model.Movie;
import com.tikal.model.MoviesResponse;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


/**
 * Created by Elad
 */

public class AppRepo extends BaseRealmRepo implements IAppRepo {

    public AppRepo(RealmConfiguration realmConfiguration) {
        super(realmConfiguration);
    }

    @Override
    public boolean saveMoviesResponse(MoviesResponse moviesResponse) {
        Realm realm = getRealm();

        realm.executeTransaction(realm1 -> {
            realm1.insert(moviesResponse.getMovies());

        });
        return true;
    }

    @Override
    public List<Movie> getMoviesResponseFromDb() {
        Realm realm = getRealm();
        RealmResults<Movie> queryResults = realm.where(Movie.class).findAll();
        realm.close();
        return queryResults;
    }

}
