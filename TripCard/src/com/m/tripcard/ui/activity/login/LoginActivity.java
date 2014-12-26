package com.m.tripcard.ui.activity.login;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.im.util.BmobLog;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.OtherLoginListener;
import cn.bmob.v3.listener.SaveListener;

import com.m.tripcard.R;
import com.m.tripcard.config.AppConfig;
import com.m.tripcard.model.verify.InputValidate;
import com.m.tripcard.tools.MLog;
import com.m.tripcard.tools.Tools;
import com.m.tripcard.tools.VerifyTools;
import com.m.tripcard.ui.activity.BaseActivity;
import com.m.tripcard.ui.activity.MainActivity;
import com.m.tripcard.ui.activity.findpassword.FindPasswordActivity;
import com.m.tripcard.ui.activity.register.RegisterActivity;

public class LoginActivity extends BaseActivity {

	private final String TAG = "LoginActivity";

	@InjectView(R.id.login_qq)
	protected ImageView loginQQ;
	@InjectView(R.id.login_weibo)
	protected ImageView loginWeibo;

	@InjectView(R.id.username_edit)
	protected EditText userNameEdit;
	@InjectView(R.id.password_edit)
	protected EditText passwordEdit;

	@InjectView(R.id.register)
	protected TextView registerBTN;
	@InjectView(R.id.forget_password)
	protected TextView forgetPwdBTN;

	@InjectView(R.id.login)
	protected ImageView loginBTN;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		isRemeberUser();

		ButterKnife.inject(this);
	}

	private void isRemeberUser() {
		BmobUser user = BmobUser.getCurrentUser(this);
		if (user != null) {
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	}

	@OnClick({ R.id.login_weibo, R.id.login_qq, R.id.login, R.id.register,
			R.id.forget_password })
	protected void onMenuClick(View view) {
		int id = view.getId();
		switch (id) {
		case R.id.login_qq:
			loginByQQ();
			break;
		case R.id.login_weibo:
			loginByWeibo();
			break;
		case R.id.login:
			login();
			break;
		case R.id.register:
			register();
			break;
		case R.id.forget_password:
			forgetPwd();
			break;
		default:
			break;
		}
	}

	private void forgetPwd() {
		Intent it = new Intent(this, FindPasswordActivity.class);
		startActivity(it);
	}

	private void register() {
		Intent it = new Intent(this, RegisterActivity.class);
		startActivity(it);
	}

	private void login() {
		Tools.hideKeyboradInput(getContext(), loginBTN);
		if (Tools.checkNetworkAvailable(getContext()) && checkForm()) {
			String userName = userNameEdit.getText().toString().trim();
			String password = passwordEdit.getText().toString().trim();

			final ProgressDialog progress = new ProgressDialog(getContext());
			String loginIngStr = getContext().getResources().getString(
					R.string.login_ing);
			progress.setMessage(loginIngStr);
			progress.setCanceledOnTouchOutside(false);
			progress.show();

			userManager.login(userName, password, new SaveListener() {

				@Override
				public void onSuccess() {
					progress.dismiss();
					Intent intent = new Intent(getContext(), MainActivity.class);
					startActivity(intent);
					finish();
				}

				@Override
				public void onFailure(int errorcode, String errorStr) {
					progress.dismiss();
					BmobLog.i(errorStr);
					Tools.toast(getContext(), errorStr);
				}
			});
		}

	}

	private boolean checkForm() {
		List<InputValidate> validates = new ArrayList<InputValidate>();
		validates.add(VerifyTools.verifyEmail(getContext(), userNameEdit));
		validates.add(VerifyTools.verifyPassword(getContext(), passwordEdit));
		for (InputValidate validate : validates) {
			if (validate.notPass()) {
				Tools.toast(getContext(), validate.getError());
				return false;
			}
		}
		return true;
	}

	private void loginByQQ() {
		BmobUser.qqLogin(this, AppConfig.QQ_ID, new OtherLoginListener() {

			@Override
			public void onSuccess(JSONObject userAuth) {
				MLog.i(TAG, "QQ登陆成功返回:" + userAuth.toString());
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
				MLog.i(TAG, "qq登陆成功返回" + msg);
			}

			@Override
			public void onCancel() {
				MLog.i(TAG, "取消weibo登陆");
			}
		});
	}

	private void loginByWeibo() {
		BmobUser.weiboLogin(this, AppConfig.WEIBO_ID, AppConfig.REDIRECT_URI,
				new OtherLoginListener() {

					@Override
					public void onSuccess(JSONObject userAuth) {
						MLog.i(TAG, "weibo登陆成功返回" + userAuth.toString());
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
						MLog.i(TAG, "weibo登陆成功返回" + msg);
					}

					@Override
					public void onCancel() {
						MLog.i(TAG, "取消weibo登陆");
					}
				});
	}
}
