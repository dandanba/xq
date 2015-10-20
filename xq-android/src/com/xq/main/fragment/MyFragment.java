package com.xq.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;

import com.xq.main.R;
import com.xq.main.fragment.base.BaseFragment;
import com.xq.main.model.User;

/**
 * 我
 * 
 * @author wanggeng
 * 
 */
public class MyFragment extends BaseFragment implements OnClickListener {
	public static MyFragment newInstance() {
		MyFragment fragment = new MyFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	@Bind(R.id.icon)
	public ImageView mIcon;
	@Bind(R.id.title)
	public TextView mTitleText;
	@Bind(R.id.text)
	public TextView mTextText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_my, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}

	@Override
	public void initData(Activity activity) {
		super.initData(activity);
	}

	@Override
	public void initView(View view) {
		super.initView(view);
		final User user = User.getUser(mBaseActivity);
		update(user);
	}

	@Override
	public void onClick(View v) {
	}

	private void update(User user) {
		mIcon.setImageResource(user.getSex() == 0 ? R.drawable.man_user_icon_default : R.drawable.woman_user_icon_default);
		mTitleText.setText(user.getUsername());
		mTextText.setText(user.getAgeText());
	}
}
