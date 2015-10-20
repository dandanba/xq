package com.xq.main.app;

import android.app.Application;
import android.util.Log;

import com.avos.avoscloud.AVOSCloud;

/**
 * 
 * @author wanggeng
 * 
 */
public class XQApplication extends Application {
	private static final String TAG = XQApplication.class.getSimpleName();
	private static XQApplication sInstance;
	private final Messager mMessager = new Messager();

	public static XQApplication getInstance() {
		if (sInstance == null) {
			Log.e(TAG, "sInstance==null");
		}
		return sInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// 如果使用美国节点，请加上这行代码 AVOSCloud.useAVCloudUS();
		AVOSCloud.initialize(this, "SvU6VoREtmKmPxTxkSwOtq6q", "9H9dJnD36gCEG6VppbL09m5j");
		sInstance = this;
	}

	public Messager getMessager() {
		return mMessager;
	}
}
