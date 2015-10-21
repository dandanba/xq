package com.xq.main.widget;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyViewPagerAdapter extends PagerAdapter {
	private List<View> mViews;

	public MyViewPagerAdapter() {
		mViews = new ArrayList<View>();
	}

	public MyViewPagerAdapter(List<View> views) {
		mViews = views;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mViews.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		final View v = mViews.get(position);
		container.addView(v, 0);
		return v;
	}

	@Override
	public int getCount() {
		return mViews.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view == obj;
	}

	public void addView(View view) {
		mViews.add(view);
	}
}