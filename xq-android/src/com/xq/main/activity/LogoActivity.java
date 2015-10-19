package com.xq.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.xq.main.R;
import com.xq.main.activity.base.BaseActivity;

public class LogoActivity extends BaseActivity {
	private final Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			removeMessages(msg.what);
			finish();
			startActivity(new Intent(LogoActivity.this, GuideActivity.class));
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		mHandler.sendEmptyMessageDelayed(1, 3000);
	}
}
