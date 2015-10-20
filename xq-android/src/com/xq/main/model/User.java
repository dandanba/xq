package com.xq.main.model;

import android.content.Context;

import com.avos.avoscloud.AVUser;
import com.xq.main.util.PreferencesUtils;

public class User extends AVUser {
	public static User getUser(Context context) {
		final String obj = PreferencesUtils.getString(context, "user");
		try {
			return (User) AVUser.parseAVObject(obj);
		} catch (Exception e) {
		}
		return null;
	}

	public static void putUser(Context context, User user) {
		PreferencesUtils.putString(context, "user", user.toString());
	}

	// private int age;
	// private int sex;
	public int getAge() {
		return getInt("age");
	}

	public void setAge(int age) {
		put("age", age);
	}

	public int getSex() {
		return getInt("sex");
	}

	public void setSex(int sex) {
		put("sex", sex);
	}
}
