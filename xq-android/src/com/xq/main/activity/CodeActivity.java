package com.xq.main.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVOSCloud;
import com.xq.main.R;
import com.xq.main.activity.base.BaseActivity;
import com.xq.main.app.XQApplication;
import com.xq.main.util.ToastUtils;

public class CodeActivity extends BaseActivity implements Handler.Callback {
	static final boolean sFree = false;
	@Bind(R.id.account_edit)
	public EditText mAccountEdit;
	@Bind(R.id.code_edit)
	public EditText mCodeEdit;
	@Bind(R.id.back_button)
	public TextView mBackButton;
	@Bind(R.id.next_button)
	public TextView mNextButton;
	@Bind(R.id.code_button)
	public TextView mCodeButton;
	final Handler mHandler = new Handler(this);
	EventHandler mEventHandler;
	boolean mSMSReady;
	private String mPhoneNumber;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_code);
		mNextButton.setText(R.string.register);
		if (!sFree && !mSMSReady) {
			SMSSDK.initSDK(this, "b52b67218b71", "51248ca84c87a6d414a861c891b8e775");
			mEventHandler = new EventHandler() {
				public void afterEvent(int event, int result, Object data) {
					final Message msg = mHandler.obtainMessage();
					msg.arg1 = event;
					msg.arg2 = result;
					msg.obj = data;
					mHandler.sendMessage(msg);
				}
			};
			SMSSDK.registerEventHandler(mEventHandler);
			mSMSReady = true;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (!sFree && mSMSReady) {
			SMSSDK.unregisterEventHandler(mEventHandler);
		}
	}

	@OnClick(R.id.back_button)
	public void backClick(View view) {
		onBackPressed();
	}

	@OnClick(R.id.next_button)
	public void nextClick(View view) {
		final String phoneNumber = mAccountEdit.getText().toString();
		final String code = mCodeEdit.getText().toString();
		mPhoneNumber = phoneNumber;
		verifyCode(phoneNumber, code);
	}

	@OnClick(R.id.code_button)
	public void codeClick(View view) {
		final String phoneNumber = mAccountEdit.getText().toString();
		sendCode(phoneNumber);
	}

	@Override
	public boolean handleMessage(Message msg) {
		int event = msg.arg1;
		int result = msg.arg2;
		Object data = msg.obj;
		if (result == SMSSDK.RESULT_COMPLETE) {// 回调完成
			if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 获取验证码成功
				ToastUtils.showToast(CodeActivity.this, "验证成功");
				// 跳转到下一个页面
				XQApplication.getInstance().getMessager().put("phone", mPhoneNumber);
				startActivity(new Intent(CodeActivity.this, RegisterActivity.class));
			} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {// 提交验证码成功
				ToastUtils.showToast(CodeActivity.this, "验证短信发送成功");
			}
		} else {
			((Throwable) data).printStackTrace();
		}
		return false;
	}

	private void verifyCode(String phoneNumber, String code) {
		if (sFree) { // 使用LeanCloud的SMS验证服务
			AVOSCloud.verifySMSCodeInBackground(code, phoneNumber, new AVMobilePhoneVerifyCallback() {
				@Override
				public void done(AVException e) {
					if (e == null) {
						ToastUtils.showToast(CodeActivity.this, "验证成功");
					} else {
						ToastUtils.showToast(CodeActivity.this, "验证失败");
					}
				}
			});
		} else {// 使用ShareSDK的短信验证服务
			SMSSDK.submitVerificationCode("86", phoneNumber, code);
		}
	}

	private void sendCode(String phone) {
		if (sFree) { // 使用LeanCloud的SMS验证服务
			new AsyncTask<String, Void, Boolean>() {
				@Override
				protected Boolean doInBackground(String... params) {
					boolean res;
					try {
						AVOSCloud.requestSMSCode(params[0], getString(R.string.app_name), "注册", 10);
						res = true;
					} catch (AVException e) {
						e.printStackTrace();
						res = false;
					}
					return res;
				}

				@Override
				protected void onPostExecute(Boolean res) {
					super.onPostExecute(res);
					if (res) {
						ToastUtils.showToast(CodeActivity.this, "验证短信发送成功");
					} else {
						ToastUtils.showToast(CodeActivity.this, "验证失败");
					}
				}
			}.execute(phone);
		} else { // 使用ShareSDK的短信验证服务
			SMSSDK.getVerificationCode("86", phone);
		}
	}
}
