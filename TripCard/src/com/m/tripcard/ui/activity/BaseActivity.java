package com.m.tripcard.ui.activity;

import com.m.tripcard.tools.MLog;

import cn.bmob.im.BmobChat;
import cn.bmob.im.BmobChatManager;
import cn.bmob.im.BmobUserManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {
	
	public BmobUserManager userManager;
	public BmobChatManager manager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		BmobChat.DEBUG_MODE = true;
		MLog.DEBUG_MODE = true;
		
		userManager = BmobUserManager.getInstance(this);
		manager = BmobChatManager.getInstance(this);
	}
	
	protected Context getContext(){
		return this;
	}
}
