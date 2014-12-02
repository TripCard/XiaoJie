package com.m.tripcard.ui.activity.login;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.OtherLoginListener;

import com.m.tripcard.R;
import com.m.tripcard.config.AppConfig;
import com.m.tripcard.tools.Tools;
import com.m.tripcard.ui.activity.BaseActivity;
import com.m.tripcard.ui.activity.MainActivity;

public class LoginActivity extends BaseActivity {
	@InjectView(R.id.login_qq)
	protected TextView loginQQ;

	@InjectView(R.id.login_weibo)
	protected TextView loginWeibo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		BmobUser user = BmobUser.getCurrentUser(this);
		if (user != null) {
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
		}

		ButterKnife.inject(this);
	}

	@OnClick(R.id.login_qq)
	protected void loginByQQ() {
		Tools.toast(getContext(), "loginByQQ");
		BmobUser.qqLogin(this, AppConfig.QQ_ID, new OtherLoginListener() {

			@Override
			public void onSuccess(JSONObject userAuth) {
				Tools.toast(getContext(), "QQ登陆成功返回:" + userAuth.toString());
				Log.i("login", "QQ登陆成功返回:" + userAuth.toString());
				// 下面则是返回的json字符
				// {
				// "qq": {
				// "openid": "B4F5ABAD717CCC93ABF3BF28D4BCB03A",
				// "access_token": "05636ED97BAB7F173CB237BA143AF7C9",
				// "expires_in": 7776000
				// }
				// }
				// 如果你想在登陆成功之后关联当前用户
				Intent intent = new Intent(LoginActivity.this,
						MainActivity.class);
				intent.putExtra("json", userAuth.toString());
				intent.putExtra("from", "qq");
				startActivity(intent);
			}

			@Override
			public void onFailure(int code, String msg) {
				Tools.toast(getContext(), "第三方登陆失败：" + msg);
			}

			@Override
			public void onCancel() {
				Tools.toast(getContext(), "取消QQ登陆");
			}
		});
	}

	@OnClick(R.id.login_weibo)
	protected void loginByWeibo() {
		Tools.toast(getContext(), "loginByWeibo");
		BmobUser.weiboLogin(this, AppConfig.WEIBO_ID, AppConfig.REDIRECT_URI,
				new OtherLoginListener() {

					@Override
					public void onSuccess(JSONObject userAuth) {
						Tools.toast(getContext(), "weibo登陆成功返回:" + userAuth);
						Log.i("login", "weibo登陆成功返回:" + userAuth.toString());
						// {
						// "weibo": {
						// "uid": "2696876973",
						// "access_token": "2.00htoVwCV9DWcB02e14b7fa50vUwjg",
						// "expires_in": 1410461999162
						// }
						// }
						Intent intent = new Intent(LoginActivity.this,
								MainActivity.class);
						intent.putExtra("json", userAuth.toString());
						intent.putExtra("from", "weibo");
						startActivity(intent);
					}

					@Override
					public void onFailure(int code, String msg) {
						// 若出现授权失败(authData error)，可清除该应用缓存，之后在授权新浪登陆
						Tools.toast(getContext(), "第三方登陆失败：" + msg);
					}

					@Override
					public void onCancel() {
						Tools.toast(getContext(), "取消weibo登陆");
					}
				});
	}
}
