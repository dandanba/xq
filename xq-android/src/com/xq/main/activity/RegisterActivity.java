package com.xq.main.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import butterknife.OnClick;

import com.avos.avoscloud.AVException;
import com.xq.main.R;
import com.xq.main.app.Messager;
import com.xq.main.app.XQApplication;
import com.xq.main.event.ActionEvent;
import com.xq.main.model.User;

import de.greenrobot.event.EventBus;

public class RegisterActivity extends AccountActivity {
	protected static final String TAG = null;
	private int mAge = 15;
	private int mSex = 0;
	private String mPhoneNumber;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Messager messager = XQApplication.getInstance().getMessager();
		mAge = (int) messager.get("age");
		mSex = (int) messager.get("sex");
		mPhoneNumber = (String) messager.get("phone");
		mNextButton.setText(R.string.register);
		mAccountEdit.setText(mPhoneNumber);
		// 设置不可编辑状态；
		mAccountEdit.setFocusable(false);
		mAccountEdit.setFocusableInTouchMode(false);
		// 设置可编辑状态
		// mAccountEdit.setFocusableInTouchMode(true);
		// mAccountEdit.setFocusable(true);
		// mAccountEdit.requestFocus();
	}

	@OnClick(R.id.next_button)
	public void nextClick(View view) {
		final String password = mPasswordEdit.getText().toString();
		new AsyncTask<String, Void, User>() {
			@Override
			protected User doInBackground(String... params) {
				final User mUser = new User();
				mUser.setSex(mSex);
				mUser.setAge(mAge);
				mUser.setMobilePhoneNumber(mPhoneNumber);
				mUser.setUsername(mPhoneNumber);
				mUser.setPassword(params[0]);
				try {
					mUser.signUp();
					return mUser;
				} catch (AVException e) {
					Log.e(TAG, "signUp", e);
				}
				return null;
			}

			protected void onPostExecute(User result) {
				if (result != null) {
					User.putUser(RegisterActivity.this, result);
					EventBus.getDefault().post(new ActionEvent("account"));
					startActivity(new Intent(RegisterActivity.this, MainActivity.class));
					finish();
				}
			};
		}.execute(password);
	}
}
