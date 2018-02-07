package com.tikal.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tikal.R;
import com.tikal.model.Trailer;
import com.tikal.utils.Utils;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.trailer_item)
public class TrailerItemView extends LinearLayout {

    @ViewById(R.id.imageViewThumbnail)
    ImageView imageViewThumbnail;

    @ViewById(R.id.textViewTrailerName)
    TextView textViewName;

    public TrailerItemView(Context context) {
        super(context);
    }

    public void bind(Trailer trailer) {

        if (trailer != null) {
            if (!TextUtils.isEmpty(trailer.getName())) {
                textViewName.setText(trailer.getName());
            }
            Utils.loadThumbnailUrl(getContext(), trailer.getKey(), imageViewThumbnail);
        }
    }
}