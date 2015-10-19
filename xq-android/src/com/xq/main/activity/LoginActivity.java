package com.xq.main.activity;

import android.os.Bundle;

import com.xq.main.R;

public class LoginActivity extends AccountActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNextButton.setText(R.string.login);
	}
}
