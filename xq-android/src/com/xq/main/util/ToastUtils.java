package com.xq.main.util;
import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	public static void showToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
}
