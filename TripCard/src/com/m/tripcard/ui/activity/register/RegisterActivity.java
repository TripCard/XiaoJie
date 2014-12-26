package com.m.tripcard.ui.activity.register;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.m.tripcard.R;
import com.m.tripcard.ui.activity.BaseActivity;

public class RegisterActivity extends BaseActivity {
	private final String TAG = "RegisterActivity";
	
	@InjectView(R.id.header_back)
	protected TextView backBTN;
	@InjectView(R.id.header_title)
	protected TextView title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		ButterKnife.inject(this);
		
		title.setText(R.string.register);
	}
}
