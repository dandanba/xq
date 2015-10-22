package com.xq.main.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

import com.xq.main.R;
import com.xq.main.dataholder.ImageDataHolder;
import com.xq.main.util.DisplayUtils;
import com.xq.main.util.ListUtils;
import com.xq.main.viewholder.ImageViewHolder;

public class FlowAdapter extends RecyclerView.Adapter<ImageViewHolder> {
	private List<ImageDataHolder> mDatas;
	private LayoutInflater mInflater;
	private Context mContext;

	public FlowAdapter(Context context) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mDatas = new ArrayList<ImageDataHolder>();
	}

	@Override
	public int getItemCount() {
		return ListUtils.getSize(mDatas);
	}

	@Override
	public void onBindViewHolder(ImageViewHolder viewHolder, int postion) {
		final ImageDataHolder dataHolder = mDatas.get(postion);
		ImageDataHolder.handleImage(mContext, dataHolder.mImageUrl, viewHolder.mImage);
	}

	@Override
	public ImageViewHolder onCreateViewHolder(ViewGroup parent, int postion) {
		View view = mInflater.inflate(R.layout.viewholder_image, null);
		final int width = DisplayUtils.dpToPxInt(mContext, 90);
		final int height = DisplayUtils.dpToPxInt(mContext, 80);
		final RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(width, height);
		view.setLayoutParams(lp);
		final ImageViewHolder viewHolder = new ImageViewHolder(view);
		ButterKnife.bind(viewHolder, view);
		return viewHolder;
	}

	public List<ImageDataHolder> getDatas() {
		return mDatas;
	}
}
