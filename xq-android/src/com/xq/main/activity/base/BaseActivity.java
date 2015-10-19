package com.xq.main.activity.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import butterknife.ButterKnife;

import com.umeng.analytics.MobclickAgent;
import com.xq.main.event.ActionEvent;

import de.greenrobot.event.EventBus;

public class BaseActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MobclickAgent.setDebugMode(true);
		// SDK在统计Fragment时，需要关闭Activity自带的页面统计，
		// 然后在每个页面中重新集成页面统计的代码(包括调用了 onResume 和 onPause 的Activity)。
		MobclickAgent.openActivityDurationTrack(false);
		// MobclickAgent.setAutoLocation(true);
		// MobclickAgent.setSessionContinueMillis(1000);
		EventBus.getDefault().register(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	// 用户登录
	public void onProfileSignIn(String userId) {
		MobclickAgent.onProfileSignIn(userId);
	}

	public void onProfileSignOff() {
		// 用户退出
		MobclickAgent.onProfileSignOff();
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		ButterKnife.bind(this);
	}

	public void onEvent(ActionEvent event) {
	}
	// public void onEvent(String event, Map<String, String> value) {
	// MobclickAgent.onEvent(this, event, value);
	// }
	//
	// public void onEvent(String event, String value) {
	// MobclickAgent.onEvent(this, event, value);
	// }
	//
	// public void onEvent(String event) {
	// MobclickAgent.onEvent(this, event);
	// }
}
