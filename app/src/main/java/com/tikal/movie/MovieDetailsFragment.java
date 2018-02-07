package com.tikal.movie;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tikal.R;
import com.tikal.adapter.TrailersAdapter;
import com.tikal.model.Movie;
import com.tikal.model.Trailer;
import com.tikal.utils.Utils;
import com.tikal.view.AspectImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_movie_details)
public class MovieDetailsFragment extends Fragment implements MovieView {

    // Constants
    public static final String MOVIE_ITEM = "movie_item";
    public static final String PANEL_MODE = "panel_mode";

    @Bean
    MoviePresenterImpl mPresenter;
    @ViewById(R.id.toolbar)
    Toolbar mToolBar;
    @ViewById(R.id.toolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @ViewById(R.id.imageViewBackDrop)
    ImageView mImageViewBackDrop;
    @ViewById(R.id.imageViewCover)
    AspectImageView mImageViewCover;
    @ViewById(R.id.textViewDetails)
    TextView mMoviePlot;
    @ViewById(R.id.textViewReleaseDate)
    TextView mMovieReleaseDate;
    @ViewById(R.id.textViewTrailers)
    TextView mTextViewTrailers;
    @ViewById(R.id.textViewRating)
    TextView mMovieRating;
    @ViewById(R.id.recyclerViewTrailers)
    RecyclerView mMovieTrailersRecyclerView;

    // Values
    private Movie mMovie;
    private boolean mTwoPanelMode;

    // Constructor
    public static MovieDetailsFragment newInstance(Movie movie, boolean twoPanelMode) {
        return MovieDetailsFragment_.builder()
                .setMovieArg(movie)
                .setPanelMode(twoPanelMode)
                .build();
    }

    @FragmentArg(MOVIE_ITEM)
    void setMovieArg(Movie movie) {
        mMovie = movie;
    }

    @FragmentArg(PANEL_MODE)
    void setPanelMode(boolean twoPanelMode) {
        mTwoPanelMode = twoPanelMode;
    }

    @AfterViews
    void afterViews() {
        mPresenter.setView(this);
        buildPanelMode();
        buildUI();

    }

    private void buildUI() {
        if (mMovie != null) {
            mMoviePlot.setText(mMovie.getOverview());
            mMovieRating.setText(getString(R.string.movie_details_rating, mMovie.getVote_average()));
            mMovieReleaseDate.setText(Utils.getDisplayReleaseDate(mMovie.getRelease_date()));
            mTextViewTrailers.setText(getString(R.string.movie_detail_trailers));
            mCollapsingToolbarLayout.setTitle(mMovie.getTitle());

            mPresenter.loadTrailers(mMovie.getId());
            if (getContext() == null)
                return;

            Utils.loadImageUrl(getActivity(), mMovie.getBackdrop_path(), mImageViewBackDrop);
            Utils.loadImageUrl(getActivity(), mMovie.getPoster_path(), mImageViewCover);

        }
    }

    /*
        Support back option only in mobile
     */
    private void buildPanelMode() {
        if (!mTwoPanelMode) {
            mToolBar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
            mToolBar.setNavigationOnClickListener(view -> {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            });
        }
    }


    @Override
    public void onTrailersLoaded(List<Trailer> trailers) {
        if (trailers != null && trailers.size() > 0) {
            TrailersAdapter adapter = new TrailersAdapter(getActivity());
            adapter.setData(trailers, mPresenter);
            mMovieTrailersRecyclerView.setAdapter(adapter);
        } else {
            mTextViewTrailers.setVisibility(View.GONE);
        }

    }

    @Override
    public void onTrailersFailure() {
    }

    @Override
    public void onTrailerClicked(Trailer trailer) {
        if (trailer.getKey() != null) {
            Utils.watchYoutubeVideo(getActivity(), trailer.getKey());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onViewDestroyed();
        }
    }
}
