package com.tikal.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.tikal.main.MainPresenterImpl;
import com.tikal.model.Movie;
import com.tikal.view.RecyclerViewAdapterBase;
import com.tikal.view.ViewWrapper;

import org.androidannotations.annotations.EBean;

import java.util.List;


/**
 * Created by Elad
 */
@EBean
public class MoviesAdapter extends RecyclerViewAdapterBase<Movie, MovieItemView> {

    private MainPresenterImpl mPresenter;
    private Context mContext;

    public MoviesAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<Movie> movies, MainPresenterImpl presenter) {
        mPresenter = presenter;
        mItems = movies;
        notifyDataSetChanged();
    }

    @Override
    protected MovieItemView onCreateItemView(ViewGroup parent, int viewType) {
        return MovieItemView_.build(mContext);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<MovieItemView> viewHolder, int position) {
        if (mItems != null && mItems.size() > 0) {
            MovieItemView view = viewHolder.getView();
            final Movie movie = mItems.get(position);
            view.bind(movie);
            view.setOnClickListener(view1 -> mPresenter.onMovieClicked(movie));
        }
    }

    @Override
    public int getItemCount() {
        if (mItems == null) {
            return 0;
        }
        return mItems.size();
    }
}
