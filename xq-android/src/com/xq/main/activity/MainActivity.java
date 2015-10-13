package com.xq.main.activity;

import java.util.ArrayList;
import java.util.List;

import me.tabak.fragmentswitcher.FragmentStateArrayPagerAdapter;
import me.tabak.fragmentswitcher.FragmentSwitcher;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xq.main.R;
import com.xq.main.fragment.HomeFragment;
import com.xq.main.fragment.MessageFragment;
import com.xq.main.fragment.MyFragment;
import com.xq.main.fragment.NearFragment;
import com.xq.main.fragment.SearchFragment;

/**
 * 主界面
 * 
 * @author wanggeng
 * 
 */
public class MainActivity extends ModelActivity implements OnClickListener {
	private final List<Fragment> mFragments = new ArrayList<Fragment>();
	private int mClickedTabId = R.id.tab_1;
	private FragmentSwitcher mFragmentSwitcher;
	private FragmentStateArrayPagerAdapter mFragmentAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initData();
		initView();
	}

	public void initData() {
		mFragments.add(HomeFragment.newInstance());
		mFragments.add(SearchFragment.newInstance());
		mFragments.add(MessageFragment.newInstance());
		mFragments.add(NearFragment.newInstance());
		mFragments.add(MyFragment.newInstance());
	}

	public void initView() {
		mFragmentSwitcher = (FragmentSwitcher) findViewById(R.id.content_layout);
		mFragmentAdapter = new FragmentStateArrayPagerAdapter(getSupportFragmentManager());
		mFragmentSwitcher.setAdapter(mFragmentAdapter);
		mFragmentAdapter.addAll(mFragments);
		final TextView button = (TextView) findViewById(R.id.tab_1);
		button.setOnClickListener(this);
		button.setTextColor(Color.parseColor("#F56034"));
		button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_1_foucsed, 0, 0);
		findViewById(R.id.tab_2).setOnClickListener(this);
		findViewById(R.id.tab_3).setOnClickListener(this);
		findViewById(R.id.tab_4).setOnClickListener(this);
		findViewById(R.id.tab_5).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		final int id = v.getId();
		TextView button;
		if (id != mClickedTabId) {
			button = (TextView) findViewById(mClickedTabId);
			button.setTextColor(Color.parseColor("#969696"));
			switch (mClickedTabId) {
			case R.id.tab_1:
				button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_1_default, 0, 0);
				break;
			case R.id.tab_2:
				button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_2_default, 0, 0);
				break;
			case R.id.tab_3:
				button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_3_default, 0, 0);
				break;
			case R.id.tab_4:
				button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_4_default, 0, 0);
				break;
			case R.id.tab_5:
				button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_5_default, 0, 0);
				break;
			default:
				break;
			}
			button = (TextView) v;
			button.setTextColor(Color.parseColor("#F56034"));
			mClickedTabId = id;
			switch (id) {
			case R.id.tab_1:
				button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_1_foucsed, 0, 0);
				mFragmentSwitcher.setCurrentItem(0);
				break;
			case R.id.tab_2:
				button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_2_foucsed, 0, 0);
				mFragmentSwitcher.setCurrentItem(1);
				break;
			case R.id.tab_3:
				button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_3_foucsed, 0, 0);
				mFragmentSwitcher.setCurrentItem(2);
				break;
			case R.id.tab_4:
				button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_4_foucsed, 0, 0);
				mFragmentSwitcher.setCurrentItem(3);
				break;
			case R.id.tab_5:
				button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_5_foucsed, 0, 0);
				mFragmentSwitcher.setCurrentItem(4);
				break;
			default:
				break;
			}
		}
	}
}
