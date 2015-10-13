package com.xq.main.fragment.base;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshAdapterViewBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.xq.main.R;
import com.xq.main.widget.SimpleArrayAdapter;

public class PullToRefreshFragment<T, P extends AbsListView> extends BaseFragment {
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	}

	public void update() {
	}
	public Mode mPullResfreshMode = Mode.BOTH;
	public PullToRefreshAdapterViewBase<P> mPullRefreshView;
	public SimpleArrayAdapter<T> mAdapter;
	public ArrayList<T> mDatas;
	public boolean mPullDownOrUp = true;
	private final Handler mRefreshHandler = new Handler() {
		public void handleMessage(Message msg) {
			mPullRefreshView.onRefreshComplete();
		};
	};

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		initData(activity);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initPullToRefresh(view);
	}

	public void refreshComplete() {
		mRefreshHandler.sendEmptyMessageDelayed(1, 100);
	}

	public void initData(Activity activity) {
		mDatas = new ArrayList<T>();
	}

	private void initPullToRefresh(View view) {
		mPullRefreshView = (PullToRefreshAdapterViewBase<P>) view.findViewById(R.id.pull_refresh_view);
		mPullRefreshView.setMode(mPullResfreshMode);
		mPullRefreshView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<P>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<P> refreshView) {
				mPullDownOrUp = true;
				update();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<P> refreshView) {
				mPullDownOrUp = false;
				update();
			}
		});
		mPullRefreshView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (parent instanceof ListView) {
					position--;
				}
				PullToRefreshFragment.this.onItemClick(parent, view, position, id);
			}
		});
		mPullRefreshView.setAdapter(mAdapter);
	}
}
