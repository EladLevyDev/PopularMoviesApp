package com.tikal.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.tikal.model.Trailer;
import com.tikal.movie.MoviePresenterImpl;
import com.tikal.view.RecyclerViewAdapterBase;
import com.tikal.view.ViewWrapper;

import org.androidannotations.annotations.EBean;

import java.util.List;


/**
 * Created by Elad
 */
@EBean
public class TrailersAdapter extends RecyclerViewAdapterBase<Trailer, TrailerItemView> {

    private MoviePresenterImpl mPresenter;
    private Context mContext;

    public TrailersAdapter(Context context) {
        mContext = context;
    }

    @Override
    protected TrailerItemView onCreateItemView(ViewGroup parent, int viewType) {
        return TrailerItemView_.build(mContext);
    }

    public void setData(List<Trailer> trailers, MoviePresenterImpl presenter) {
        mPresenter = presenter;
        mItems = trailers;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewWrapper<TrailerItemView> viewHolder, int position) {
        if (mItems != null && mItems.size() > 0) {
            TrailerItemView view = viewHolder.getView();
            final Trailer trailer = mItems.get(position);
            view.bind(trailer);
            view.setOnClickListener(view1 -> mPresenter.onTrailerClicked(trailer));
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
