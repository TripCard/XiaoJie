package com.m.tripcard.tools;

import android.util.Log;

public class MLog {
	public static boolean DEBUG_MODE = false;

	public static void v(String TAG, String msg) {
		if (DEBUG_MODE) {
			Log.v(TAG, msg);
		}
	}

	public static void d(String TAG, String msg) {
		if (DEBUG_MODE) {
			Log.d(TAG, msg);
		}
	}

	public static void i(String TAG, String msg) {
		if (DEBUG_MODE) {
			Log.i(TAG, msg);
		}
	}

	public void w(String TAG, String msg) {
		if (DEBUG_MODE) {
			Log.w(TAG, msg);
		}
	}

	public static void e(String TAG, String msg) {
		if (DEBUG_MODE) {
			Log.e(TAG, msg);
		}
	}

}
