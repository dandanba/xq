package com.xq.main.dataholder.base;

import java.io.File;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xq.main.R;

public class BaseDataHolder {
	public int mType;

	public static void handleImage(Context context, String url, ImageView image) {
		if (url.startsWith("http://")) {
			Picasso.with(context).load(url).into(image);
		} else if (url.startsWith("drawable://")) {
			// TODO
		} else if (url.startsWith("file://")) {
			Picasso.with(context).load(new File(url.substring("file://".length()))).placeholder(R.drawable.default_error).error(R.drawable.default_error).into(image);
		}
	}
}
