package com.xq.main.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.OnClick;

import com.wangjie.wheelview.WheelView;
import com.xq.main.R;
import com.xq.main.activity.base.BaseActivity;
import com.xq.main.app.Messager;
import com.xq.main.app.XQApplication;
import com.xq.main.event.ActionEvent;

public class SelectActivity extends BaseActivity {
	@Bind(R.id.wheel)
	public WheelView mAgeWheel;
	@Bind(R.id.man)
	public ImageView mManButton;
	@Bind(R.id.woman)
	public ImageView mWomanButton;
	private final static int sOffet = 1;
	private final List<String> mAges = new ArrayList<>();
	private int mAge = 15;
	private int mSex = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
		mAgeWheel.setOffset(sOffet);
		for (int i = 0; i < 30; i++) {
			mAges.add(String.format("%1$d岁", 15 + i));
		}
		mAgeWheel.setItems(mAges);
		mAgeWheel.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
			@Override
			public void onSelected(int selectedIndex, String item) {
				mAge = selectedIndex - sOffet + 15;
			}
		});
		manClick(null);
	}

	@OnClick(R.id.man)
	public void manClick(View view) {
		mSex = 0;
		mManButton.setImageResource(R.drawable.radio_men_button_normal);
		mWomanButton.setImageResource(R.drawable.radio_women_button_pressed);
	}

	@OnClick(R.id.woman)
	public void womanClick(View view) {
		mSex = 1;
		mManButton.setImageResource(R.drawable.radio_men_button_pressed);
		mWomanButton.setImageResource(R.drawable.radio_women_button_normal);
	}

	@OnClick(R.id.next_button)
	public void nextClick(View view) {
		final Messager messager = XQApplication.getInstance().getMessager();
		messager.put("age", mAge);
		messager.put("sex", mSex);
		startActivity(new Intent(this, CodeActivity.class));
	}

	@OnClick(R.id.login_button)
	public void loginClick(View view) {
		startActivity(new Intent(this, LoginActivity.class));
	}

	@Override
	public void onEvent(ActionEvent event) {
		if (event.mAction.equals("account")) {
			finish();
		}
	}
}
