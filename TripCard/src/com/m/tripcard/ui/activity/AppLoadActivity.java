package com.m.tripcard.ui.activity;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;

import com.m.tripcard.config.AppConfig;
import com.m.tripcard.ui.activity.login.LoginActivity;

import android.content.Intent;
import android.os.Bundle;

public class AppLoadActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// init bmob in app first activity
		Bmob.initialize(this, AppConfig.BMOB_ID);
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				jump2LoginActivity();
			}

		}, 500);
	}

	private void jump2LoginActivity() {
		Intent it=new Intent(getContext(),LoginActivity.class);
		startActivity(it);
		finish();
	}
}
