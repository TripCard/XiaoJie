package com.m.tripcard.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.EditText;
import android.widget.Toast;

public class Tools {
	public static final String RegularAccout = "^[a-zA-Z][\\w_]+$";
	public static final String RegularMobile = "^1[358]\\d{9}";
	public static final String RegularEmail = "^[\\w_]+@[\\w_]+.[com|cn|net]$";
	public static final String RegularPassword = "^[^\\s\u4e00-\u9fa5]";

	public static boolean editTextIsNullOrEmty(EditText editText) {
		if (editText == null)
			return true;
		String inputStr = editText.getText().toString();
		if (inputStr.trim().equals(""))
			return true;
		return false;
	}

	public static boolean matchStr(EditText editText, String regularStr) {
		String inputStr = editText.getText().toString();
		if (matchStr(inputStr, regularStr))
			return true;
		return false;
	}

	public static boolean matchStr(String str, String regularStr) {
		return true;
	}

	public static void toast(Context context, String error) {
		Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
	}

	public static void toast(Context context, int rourceId) {
		toast(context, context.getResources().getString(rourceId));
	}

	public static boolean checkNetworkAvailable(Context context) {
		NetworkInfo info = getNetworkInfo(context);
		if (info != null) {
			return info.isAvailable();
		}
		return false;
	}

	private static NetworkInfo getNetworkInfo(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo();
	}
	
	/** 检查是否有网络 */
	public static boolean isNetworkAvailable(Context context) {
		NetworkInfo info = getNetworkInfo(context);
		if (info != null) {
			return info.isAvailable();
		}
		return false;
	}

	/** 检查是否是WIFI */
	public static boolean isWifi(Context context) {
		NetworkInfo info = getNetworkInfo(context);
		if (info != null) {
			if (info.getType() == ConnectivityManager.TYPE_WIFI)
				return true;
		}
		return false;
	}

	/** 检查是否是移动网络 */
	public static boolean isMobile(Context context) {
		NetworkInfo info = getNetworkInfo(context);
		if (info != null) {
			if (info.getType() == ConnectivityManager.TYPE_MOBILE)
				return true;
		}
		return false;
	}

	/** 检查SD卡是否存在 */
	public static boolean checkSdCard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;
	}
}
