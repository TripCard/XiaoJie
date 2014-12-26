package com.m.tripcard.ui.activity.findpassword;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.EmailVerifyListener;

import com.m.tripcard.R;
import com.m.tripcard.model.verify.InputValidate;
import com.m.tripcard.tools.MLog;
import com.m.tripcard.tools.Tools;
import com.m.tripcard.tools.VerifyTools;
import com.m.tripcard.ui.activity.BaseActivity;

public class FindPasswordActivity extends BaseActivity {
	private final String TAG = "FindPasswordActivity";

	@InjectView(R.id.header_back)
	protected TextView backBTN;
	@InjectView(R.id.header_title)
	protected TextView title;
	@InjectView(R.id.reset_pwd_btn)
	protected Button resetPwdBTN;

	@InjectView(R.id.reset_mail)
	protected EditText resetMail;
	@InjectView(R.id.countdown_time)
	protected TextView timeTips;

	private Timer timer;

	private final int COUNTDOWNTIME = 60;
	private int countDownTimer;

	Handler countDownHandler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			int time = msg.what;
			if (time >= 0) {
				timeTips.setVisibility(View.VISIBLE);
				String tipsStr = String.format(
						getContext().getText(R.string.send_email_success)
								.toString(), time + "");
				timeTips.setText(tipsStr);
				resetPwdBTN.setEnabled(false);
			} else {
				timeTips.setText("");
				timeTips.setVisibility(View.GONE);
				resetPwdBTN.setEnabled(true);
			}
			return true;
		}
	});

	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			if (countDownTimer >= 0) {
				countDownHandler.sendEmptyMessage(countDownTimer);
			}
			countDownTimer--;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findpassword);

		ButterKnife.inject(this);

		title.setText(R.string.forget_password);
	}

	@OnClick({ R.id.header_back, R.id.reset_pwd_btn })
	protected void onBTNCilck(View view) {
		switch (view.getId()) {
		case R.id.header_back:
			finish();
			break;
		case R.id.reset_pwd_btn:
			InputValidate validate = VerifyTools.verifyEmail(getContext(),
					resetMail);
			if (validate.notPass()) {
				Tools.toast(getContext(), validate.getError());
			} else {
				sendResetPasswordEmail();
			}
			break;
		default:
			break;
		}

	}

	private void sendResetPasswordEmail() {
		String email = resetMail.getText().toString().trim();
		BmobUser.requestEmailVerify(getContext(), email,
				new EmailVerifyListener() {
					@Override
					public void onSuccess() {
						countDownTimer = COUNTDOWNTIME;
						timer = new Timer();
						timer.schedule(task, 0, 1000);
					}

					@Override
					public void onFailure(int code, String error) {
						MLog.i(TAG, code + ":" + error);
						StringBuffer errorMsg = new StringBuffer();
						errorMsg.append(getContext().getResources().getString(
								R.string.send_email_fail));
						switch (code) {
						case 205:
							errorMsg.append(getContext().getResources()
									.getString(
											R.string.send_email_fail_error_205));
							break;
						default:
							break;
						}
						Tools.toast(getContext(), errorMsg.toString(), true);
					}
				});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}
}
