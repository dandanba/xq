package com.xq.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.xq.main.R;
import com.xq.main.dataholder.UserDataHolder;
import com.xq.main.fragment.base.PullToRefreshFragment;
import com.xq.main.viewholder.UserViewHolder;

/**
 * 搜索
 * 
 * @author wanggeng
 * 
 */
public class SearchFragment extends PullToRefreshFragment<UserDataHolder, UserViewHolder, GridView> implements OnClickListener {
	public static SearchFragment newInstance() {
		SearchFragment fragment = new SearchFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mPullResfreshMode = Mode.DISABLED;
		return inflater.inflate(R.layout.fragment_ptr_grid, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView(view);
		update();
	}

	@Override
	public void initData(Activity activity) {
		super.initData(activity);
	}

	@Override
	public void initView(View view) {
		super.initView(view);
	}

	@Override
	public void update() {
	}

	@Override
	public void onClick(View v) {
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		final UserDataHolder dataHolder = mDatas.get(position);
	}

	@Override
	public UserViewHolder createViewHolder(View convertView, ViewGroup parent, int position, UserDataHolder data) {
		return null;
	}

	@Override
	public void handleView(ViewGroup parent, View convertView, UserViewHolder viewHolder, int position, UserDataHolder data) {
	}
}
