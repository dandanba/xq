package com.xq.main.activity;

import android.os.Bundle;

import com.xq.main.R;
import com.xq.main.app.Messager;
import com.xq.main.app.XQApplication;

public class RegisterActivity extends AccountActivity {
	private int mAge = 15;
	private int mSex = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Messager messager = XQApplication.getInstance().getMessager();
		mAge = (int) messager.get("age");
		mSex = (int) messager.get("sex");
		mNextButton.setText(R.string.register);
	}
}
