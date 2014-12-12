package com.m.tripcard.ui.activity;

import cn.bmob.v3.BmobUser;
import android.os.Bundle;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		BmobUser.logOut(getContext());
		super.onDestroy();
	}
}
