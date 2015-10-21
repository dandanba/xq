package com.xq.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.xq.main.R;
import com.xq.main.activity.base.BaseActivity;
import com.xq.main.model.User;

public class LogoActivity extends BaseActivity {
	private final Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			removeMessages(msg.what);
			final boolean hasGuide = GuideActivity.hasGuide(LogoActivity.this);
			if (hasGuide) {
				User user = User.getUser(LogoActivity.this);
				if (user == null) {
					startActivity(new Intent(LogoActivity.this, SelectActivity.class));
				} else {
					startActivity(new Intent(LogoActivity.this, MainActivity.class));
				}
			} else {
				startActivity(new Intent(LogoActivity.this, GuideActivity.class));
			}
			finish();
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		mHandler.sendEmptyMessageDelayed(1, 3000);
	}
}
