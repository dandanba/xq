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
import com.xq.main.event.ActionEvent;

import de.greenrobot.event.EventBus;

public class LoginActivity extends AccountActivity {
	@Bind(R.id.account_edit)
	public EditText mAccountEdit;
	@Bind(R.id.password_edit)
	public ImageView mPasswordEdit;
	@Bind(R.id.back_button)
	public TextView mBackButton;
	@Bind(R.id.next_button)
	public TextView mNextButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNextButton.setText(R.string.login);
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
