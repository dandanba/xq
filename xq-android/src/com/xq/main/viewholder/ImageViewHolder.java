package com.xq.main.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Bind;

import com.xq.main.R;

public class ImageViewHolder extends RecyclerView.ViewHolder {
	public ImageViewHolder(View view) {
		super(view);
	}

	@Bind(R.id.image)
	public ImageView mImage;
}
