package com.xq.main.fragment.base;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import butterknife.Bind;

import com.handmark.pulltorefresh.library.PullToRefreshAdapterViewBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.xq.main.R;
import com.xq.main.dataholder.base.BaseDataHolder;
import com.xq.main.viewholder.base.BaseViewHolder;
import com.xq.main.widget.SimpleArrayAdapter;

public abstract class PullToRefreshFragment<D extends BaseDataHolder, V extends BaseViewHolder, P extends AbsListView> extends BaseFragment {
	/**
	 * 
	 * @param convertView
	 * @param parent
	 * @param position
	 * @param data
	 * @return
	 */
	public abstract V createViewHolder(View convertView, ViewGroup parent, int position, D data);

	/**
	 * 
	 * @param parent
	 * @param convertView
	 * @param viewHolder
	 * @param position
	 * @param data
	 */
	public abstract void handleView(ViewGroup parent, View convertView, V viewHolder, int position, D data);

	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				mPullRefreshView.onRefreshComplete();
				break;
			default:
				break;
			}
		};
	};
	public int mViewTypeCount = 1;
	public Mode mPullResfreshMode = Mode.DISABLED;
	public SimpleArrayAdapter<D> mAdapter;
	public ArrayList<D> mDatas;
	public boolean mPullDownOrUp = true;
	@Bind(R.id.pull_refresh_view)
	public PullToRefreshAdapterViewBase<P> mPullRefreshView;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		initData(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}

	public void initData(Activity activity) {
		mDatas = new ArrayList<D>();
		mAdapter = new SimpleArrayAdapter<D>(activity, 0, mDatas) {
			public int getItemViewType(int position) {
				return mDatas.get(position).mType;
			}

			public int getViewTypeCount() {
				return mViewTypeCount;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				final D data = getItem(position);
				V v;
				if (convertView == null) {
					v = createViewHolder(convertView, parent, position, data);
					convertView = v.mConvertView;
				} else {
					v = (V) convertView.getTag();
				}
				handleView(parent, convertView, v, position, data);
				return convertView;
			}
		};
	}

	public void initView(View view) {
		initPullToRefresh(view);
	}

	private void initPullToRefresh(View view) {
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

	public void refreshComplete() {
		mHandler.sendEmptyMessageDelayed(1, 500);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	}

	public void update() {
	}
}
