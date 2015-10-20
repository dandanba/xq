package com.xq.main.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import butterknife.OnClick;

import com.avos.avoscloud.AVException;
import com.xq.main.R;
import com.xq.main.event.ActionEvent;
import com.xq.main.model.User;

import de.greenrobot.event.EventBus;

public class LoginActivity extends AccountActivity {
	protected static final String TAG = LoginActivity.class.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNextButton.setText(R.string.login);
	}

	@OnClick(R.id.next_button)
	public void nextClick(View view) {
		final String phoneNumber = mAccountEdit.getText().toString();
		final String password = mPasswordEdit.getText().toString();
		new AsyncTask<String, Void, User>() {
			@Override
			protected User doInBackground(String... params) {
				try {
					return User.loginByMobilePhoneNumber(params[0], params[1], User.class);
				} catch (AVException e) {
					Log.e(TAG, "login", e);
				}
				return null;
			}

			@Override
			protected void onPostExecute(User result) {
				super.onPostExecute(result);
				if (result != null) {
					EventBus.getDefault().post(new ActionEvent("account"));
					startActivity(new Intent(LoginActivity.this, MainActivity.class));
					finish();
					User.putUser(LoginActivity.this, result);
				}
			}
		}.execute(phoneNumber, password);
	}
}
