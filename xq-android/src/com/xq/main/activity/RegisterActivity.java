package com.xq.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;

import com.xq.main.R;
import com.xq.main.activity.base.BaseActivity;
import com.xq.main.app.Messager;
import com.xq.main.app.XQApplication;
import com.xq.main.event.ActionEvent;

import de.greenrobot.event.EventBus;

public class RegisterActivity extends AccountActivity {
	@Bind(R.id.account_edit)
	public EditText mAccountEdit;
	@Bind(R.id.password_edit)
	public ImageView mPasswordEdit;
	@Bind(R.id.back_button)
	public TextView mBackButton;
	@Bind(R.id.next_button)
	public TextView mNextButton;
	private int mAge = 15;
	private int mSex = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		final Messager messager = XQApplication.getInstance().getMessager();
		mAge = (int) messager.get("age");
		mSex = (int) messager.get("sex");
		mNextButton.setText(R.string.register);
	}

	@OnClick(R.id.next_button)
	public void nextClick(View view) {
		EventBus.getDefault().post(new ActionEvent("account"));
		startActivity(new Intent(this, MainActivity.class));
	}

	@OnClick(R.id.back_button)
	public void backClick(View view) {
		onBackPressed();
	}
}
