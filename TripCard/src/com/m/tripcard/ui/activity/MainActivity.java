package com.m.tripcard.ui.activity;

import com.m.tripcard.R;
import com.m.tripcard.ui.fragment.LeftMainFragment;
import com.m.tripcard.ui.fragment.MasterMainFragment;
import com.m.tripcard.ui.fragment.RightMainFragment;
import com.m.tripcard.ui.view.FlipperLayout;

import cn.bmob.v3.BmobUser;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class MainActivity extends BaseActivity {

	private FlipperLayout mFlipperLayout;

	private LeftMainFragment leftFragment;
	private RightMainFragment rightFragment;
	private MasterMainFragment masterFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trip_card_main);
		initView();

		initFragment();

	}

	private void initFragment() {
		FrameLayout leftView = new FrameLayout(this);
		leftView.setId(R.id.flipper_left);
		FrameLayout rightView = new FrameLayout(this);
		rightView.setId(R.id.flipper_right);
		FrameLayout masterView = new FrameLayout(this);
		masterView.setId(R.id.flipper_master);

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		mFlipperLayout.setLeftView(leftView, params);
		mFlipperLayout.setRightView(rightView, params);
		mFlipperLayout.setMasterView(masterView, params);

		leftFragment = new LeftMainFragment();
		rightFragment = new RightMainFragment();
		masterFragment = new MasterMainFragment();

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();

		ft.replace(R.id.flipper_left, leftFragment);
		ft.replace(R.id.flipper_right, rightFragment);
		ft.replace(R.id.flipper_master, masterFragment);
		ft.commit();

	}

	private void initView() {
		mFlipperLayout = (FlipperLayout) findViewById(R.id.trip_card_main_layout);
	}

	@Override
	protected void onDestroy() {
		BmobUser.logOut(getContext());
		super.onDestroy();
	}
}
