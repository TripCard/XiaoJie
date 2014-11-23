package com.m.tripcard.tools;

import com.m.tripcard.R;
import com.m.tripcard.bean.verify.InputValidate;
import android.content.Context;
import android.widget.EditText;

public class VerifyTools {
	public static InputValidate verifyAccount(Context context,
			EditText accountName) {
		final InputValidate inputValidate = new InputValidate();
		if (Tools.editTextIsNullOrEmty(accountName)) {
			inputValidate.setError(context.getResources().getString(
					R.string.account_is_null));
		} else if (!Tools.matchStr(accountName, Tools.RegularAccout)) {
			inputValidate.setError(context.getResources().getString(
					R.string.account_is_illegal));
		} else {
			inputValidate.setPass(true);
		}
		return inputValidate;
	}

	public static InputValidate verifyMobile(Context context,
			EditText accountMobile) {
		final InputValidate inputValidate = new InputValidate();
		if (Tools.editTextIsNullOrEmty(accountMobile)) {
			inputValidate.setError(context.getResources().getString(
					R.string.mobile_is_null));
		} else if (!Tools.matchStr(accountMobile, Tools.RegularMobile)) {
			inputValidate.setError(context.getResources().getString(
					R.string.mobile_is_illegal));
		} else {
			inputValidate.setPass(true);
		}
		return inputValidate;
	}

	public static InputValidate verifyEmail(Context context,
			EditText accountEmail) {
		final InputValidate inputValidate = new InputValidate();
		if (Tools.editTextIsNullOrEmty(accountEmail)) {
			inputValidate.setError(context.getResources().getString(
					R.string.email_is_null));
		} else if (!Tools.matchStr(accountEmail, Tools.RegularEmail)) {
			inputValidate.setError(context.getResources().getString(
					R.string.email_is_illegal));
		} else {
			inputValidate.setPass(true);
		}
		return inputValidate;
	}

	public static InputValidate verifyPassword(Context context,
			EditText accountPWD) {
		final InputValidate inputValidate = new InputValidate();
		if (Tools.editTextIsNullOrEmty(accountPWD)) {
			inputValidate.setError(context.getResources().getString(
					R.string.password_is_null));
		} else if (!Tools.matchStr(accountPWD, Tools.RegularPassword)) {
			inputValidate.setError(context.getResources().getString(
					R.string.password_is_illegal));
		} else {
			inputValidate.setPass(true);
		}
		return inputValidate;
	}
}
