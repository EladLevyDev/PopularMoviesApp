package com.tikal.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewAdapterBase<T, V extends View> extends RecyclerView.Adapter<ViewWrapper<V>> {

    protected List<T> mItems = new ArrayList<T>();

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public final ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<V>(onCreateItemView(parent, viewType));
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

}