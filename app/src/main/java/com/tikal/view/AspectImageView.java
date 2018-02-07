
package com.tikal.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.tikal.R;

import org.androidannotations.annotations.EView;

@EView
public class AspectImageView extends ImageView {

    private float mRatio = 0;
    private AspectRatioSource mAspectRatioSource = null;

    public AspectImageView(Context context) {
        super(context);
    }

    public AspectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AspectImageView);
        mRatio = a.getFloat(R.styleable.AspectImageView_imageRatio, 0);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        float localRatio = mRatio;

        if (localRatio == 0.0 && mAspectRatioSource != null
                && mAspectRatioSource.getHeight() > 0) {
            localRatio =
                    (float) mAspectRatioSource.getWidth()
                            / (float) mAspectRatioSource.getHeight();
        }

        if (localRatio == 0.0) {
            super.onMeasure(widthSpec, heightSpec);
        } else {
            int lockedWidth = MeasureSpec.getSize(widthSpec);
            int lockedHeight = MeasureSpec.getSize(heightSpec);

            if (lockedWidth == 0 && lockedHeight == 0) {
                throw new IllegalArgumentException(
                        "Both width and height cannot be zero -- watch out for scrollable containers");
            }

            // Get the padding of the border background.
            int hPadding = getPaddingLeft() + getPaddingRight();
            int vPadding = getPaddingTop() + getPaddingBottom();

            // Resize the preview frame with correct aspect ratio.
            lockedWidth -= hPadding;
            lockedHeight -= vPadding;

            if (lockedHeight > 0 && (lockedWidth > lockedHeight * localRatio)) {
                lockedWidth = (int) (lockedHeight * localRatio + .5);
            } else {
                lockedHeight = (int) (lockedWidth / localRatio + .5);
            }

            // Add the padding of the border.
            lockedWidth += hPadding;
            lockedHeight += vPadding;

            // Ask children to follow the new preview dimension.
            super.onMeasure(MeasureSpec.makeMeasureSpec(lockedWidth, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(lockedHeight, MeasureSpec.EXACTLY));
        }
    }

    public interface AspectRatioSource {
        int getWidth();

        int getHeight();
    }


}
