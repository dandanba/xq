package com.xq.main.app;

import android.app.Application;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;

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
		sInstance = this;
	}

	public Messager getMessager() {
		return mMessager;
	}
}
