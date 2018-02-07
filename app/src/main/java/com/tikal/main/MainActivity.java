package com.tikal.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.gturedi.views.StatefulLayout;
import com.tikal.R;
import com.tikal.adapter.MoviesAdapter;
import com.tikal.model.Movie;
import com.tikal.movie.MovieDetailsActivity_;
import com.tikal.movie.MovieDetailsFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.parceler.Parcels;

import java.util.List;


@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements MainView {

    // Constants
    private static final int TABLET_GRID_COUNT = 3;
    private static final int MOBILE_GRID_COUNT = 2;

    @Bean
    MainPresenterImpl mPresenter;

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @ViewById(R.id.stateful)
    StatefulLayout mStateful;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    MoviesAdapter mMoviesAdapter;

    private boolean mTwoPanel;

    @AfterViews
    void afterViews() {
        mPresenter.setView(this);
        buildUI();
        loadData();
    }

    private void buildUI() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Top Movies");

        if (findViewById(R.id.movieDetailsFragment) != null) {
            mTwoPanel = true;
        }
        mMoviesAdapter = new MoviesAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), mTwoPanel ? TABLET_GRID_COUNT : MOBILE_GRID_COUNT);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMoviesAdapter);

        // OnCreate remove ,
        // app in mobile
    }

    private void loadData() {
        mStateful.showLoading();
        mPresenter.loadData();
    }

    @Override
    public void onDataIsReady(List<Movie> movies) {
        if (movies.size() == 0) {
            mStateful.showEmpty();
            return;
        }

        mMoviesAdapter.setData(movies, mPresenter);
        if (mTwoPanel) {
            showItemDetails(movies.get(0));
        }
        mStateful.showContent();
    }

    @Override
    public void onDataIsFailure() {
        mStateful.showError(getString(R.string.something_went_wrong), view -> {
            // TODO: LOAD DATA FROM DB
        });
    }

    @Override
    public void showItemDetails(Movie movie) {
        if (mTwoPanel) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movieDetailsFragment, MovieDetailsFragment.newInstance(movie, mTwoPanel))
                    .commit();
        } else {
            Intent intent = new Intent(MainActivity.this, MovieDetailsActivity_.class);
            intent.putExtra(MovieDetailsFragment.MOVIE_ITEM, Parcels.wrap(movie));
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onViewDestroyed();
        }
    }

}