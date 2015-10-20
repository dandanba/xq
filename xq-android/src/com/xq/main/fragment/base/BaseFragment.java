package com.xq.main.fragment.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import butterknife.ButterKnife;

import com.xq.main.activity.base.BaseActivity;

import de.greenrobot.event.EventBus;

public class BaseFragment extends Fragment {
	private BaseActivity mBaseActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		initData(activity);
		EventBus.getDefault().register(this);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		EventBus.getDefault().unregister(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public void onEvent(Object event) {
	}

	public void initData(Activity activity) {
		mBaseActivity = (BaseActivity) activity;
	}

	public void initView(View view) {
	}
}
