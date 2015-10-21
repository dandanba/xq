package com.xq.main.fragment;

import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;

import com.xq.main.R;
import com.xq.main.adapter.FlowAdapter;
import com.xq.main.dataholder.ImageDataHolder;
import com.xq.main.fragment.base.BaseFragment;
import com.xq.main.model.User;
import com.xq.main.util.ListUtils;

/**
 * 我
 * 
 * @author wanggeng
 * 
 */
public class MyFragment extends BaseFragment implements OnClickListener {
	private static final int REQUEST_IMAGE = 2;

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
	@Bind(R.id.flow_layout)
	public RecyclerView mRecyclerView;
	private FlowAdapter mFlowAdapter;

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
		// 设置布局管理器
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mBaseActivity);
		linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRecyclerView.setLayoutManager(linearLayoutManager);
		mFlowAdapter = new FlowAdapter(mBaseActivity);
		mRecyclerView.setAdapter(mFlowAdapter);
		update(user);
	}

	@Override
	public void onClick(View v) {
	}

	@OnClick(R.id.icon_button)
	public void onAddImageClick(View view) {
		showMultiSelector(false, true, 5);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_IMAGE) {
			if (resultCode == Activity.RESULT_OK) {
				ArrayList<String> mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
				final int size = ListUtils.getSize(mSelectPath);
				for (int i = 0; i < size; i++) {
					mFlowAdapter.getDatas().add(new ImageDataHolder("file://" + mSelectPath.get(i)));
					mFlowAdapter.notifyDataSetChanged();
				}
			}
		}
	}

	private void update(User user) {
		if (user != null) {
			mIcon.setImageResource(user.getSex() == 0 ? R.drawable.man_user_icon_default : R.drawable.woman_user_icon_default);
			mTitleText.setText(user.getUsername());
			mTextText.setText(user.getAgeText());
		}
	}

	public void showMultiSelector(boolean single, boolean showCamera, int maxNum) {
		int selectedMode = MultiImageSelectorActivity.MODE_MULTI;
		if (single) {
			selectedMode = MultiImageSelectorActivity.MODE_SINGLE;
		} else {
			selectedMode = MultiImageSelectorActivity.MODE_MULTI;
		}
		Intent intent = new Intent(mBaseActivity, MultiImageSelectorActivity.class);
		// 是否显示拍摄图片
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, showCamera);
		// 最大可选择图片数量
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);
		// 选择模式
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, selectedMode);
		startActivityForResult(intent, REQUEST_IMAGE);
	}
}
