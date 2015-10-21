/*
 * Copyright 2014 Jacob Tabak, adapted from ViewPager in the Android Support Library
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.tabak.fragmentswitcher;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * A fragment switcher similar to a {@link android.support.v4.view.ViewPager} that cannot be swiped and does not keep offscreen fragments like a ViewPager.
 * 
 * The instance state of the fragments in the adapter will be managed automatically. Perfect for use with tabs, navigation drawers, or any interface that switches fragments.
 * 
 * Compatible adapters include {@link me.tabak.fragmentswitcher.FragmentStateArrayPagerAdapter}, {@link me.tabak.fragmentswitcher.FragmentArrayPagerAdapter}, {@link android.support.v4.app.FragmentStatePagerAdapter}, {@link android.support.v4.app.FragmentStatePagerAdapter}
 */
@SuppressWarnings("UnusedDeclaration")
public class FragmentSwitcher extends ViewPager {
	private boolean noScroll = true;

	public FragmentSwitcher(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FragmentSwitcher(Context context) {
		super(context);
	}

	public void setNoScroll(boolean noScroll) {
		this.noScroll = noScroll;
	}

	@Override
	public void scrollTo(int x, int y) {
		super.scrollTo(x, y);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (noScroll)
			return false;
		else
			return super.onTouchEvent(arg0);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (noScroll)
			return false;
		else
			return super.onInterceptTouchEvent(arg0);
	}

	@Override
	public void setCurrentItem(int item) {
		super.setCurrentItem(item, false);
	}
}