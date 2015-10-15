package com.xq.main.viewholder.base;

import android.view.View;
import butterknife.ButterKnife;

public class BaseViewHolder {
	public View mConvertView;

	public BaseViewHolder(View view) {
		ButterKnife.bind(this, view);
	}
}
