package com.xq.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.xq.main.R;
import com.xq.main.activity.base.BaseActivity;
import com.xq.main.util.PreferencesUtils;
import com.xq.main.widget.MyViewPager;
import com.xq.main.widget.MyViewPagerAdapter;

public class GuideActivity extends BaseActivity {
	public static boolean hasGuide(Context context) {
		return PreferencesUtils.getBoolean(context, "has_guide");
	}

	@Bind(R.id.pager)
	public MyViewPager mViewPager;
	public ImagePagerAdapter mImageAdapter;
	public int[] mImages = new int[] { R.drawable.app_guidance_item_1, R.drawable.app_guidance_item_2, R.drawable.app_guidance_item_3, };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		PreferencesUtils.putBoolean(this, "has_guide", true);
		mImageAdapter = new ImagePagerAdapter(mImages);
		mViewPager.setAdapter(mImageAdapter);
	}

	class ImagePagerAdapter extends MyViewPagerAdapter {
		@Bind(R.id.image)
		ImageView mImageView;
		@Bind(R.id.start_button)
		ImageView mStartButton;

		@OnClick(R.id.start_button)
		void onStartButtonClick(View v) {
			startActivity(new Intent(GuideActivity.this, SelectActivity.class));
			finish();
		}

		public ImagePagerAdapter(int[] images) {
			super();
			View view;
			final int size = images.length;
			for (int i = 0; i < size; i++) {
				view = getLayoutInflater().inflate(R.layout.layout_image_item, null);
				ButterKnife.bind(this, view);
				mImageView.setImageResource(images[i]);
				if (size - 1 == i) {
					mStartButton.setVisibility(View.VISIBLE);
				} else {
					mStartButton.setVisibility(View.GONE);
				}
				addView(view);
			}
		}
	}
}
