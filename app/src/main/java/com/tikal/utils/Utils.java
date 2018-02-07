package com.tikal.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tikal.BuildConfig;
import com.tikal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by Elad on 3/28/16.
 */
public class Utils {

    // Constants
    public static final int IMAGE_SIZE = 500;
    public static final String BASE_IMAGE_URL = BuildConfig.BASE_IMAGE_URL + IMAGE_SIZE;

    public static String buildImageUrl(String image_path) {
        return BASE_IMAGE_URL + image_path;
    }


    public static void watchYoutubeVideo(Activity activity, String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            activity.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + id));
            activity.startActivity(intent);
        }
    }

    public static String getDisplayReleaseDate(String releaseDate) {
        if (TextUtils.isEmpty(releaseDate)) return "";
        try {
            final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DATE_FORMAT.parse(releaseDate));
            return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + calendar.get(Calendar.YEAR);
        } catch (ParseException e) {
            return "";
        }
    }

    public static void loadImageUrl(Context context, String path, ImageView imageViewBackDrop) {
        String url = buildImageUrl(path);
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url).placeholder(R.color.colorGray).into(imageViewBackDrop);
        } else {
            imageViewBackDrop.setImageDrawable(null);
        }
    }

    public static void loadThumbnailUrl(Context context, String videoId, ImageView imageView) {
        String thumbnailUrl = "http://img.youtube.com/vi/" + videoId + "/0.jpg";
        Picasso.with(context).load(thumbnailUrl).into(imageView);
    }

}
