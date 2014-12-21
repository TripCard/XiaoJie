package com.m.tripcard.ui.activity;

import com.m.tripcard.R;
import com.m.tripcard.ui.fragment.LeftMainFragment;
import com.m.tripcard.ui.fragment.MasterMainFragment;
import com.m.tripcard.ui.fragment.RightMainFragment;
import com.m.tripcard.ui.view.FlipperLayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class TripCardActivity extends FragmentActivity {

	private FlipperLayout mFlipperLayout;

	private LeftMainFragment leftFragment;
	private RightMainFragment rightFragment;
	private MasterMainFragment masterFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.trip_card_main);
		initView();

		initFragment();

	}

	private void initFragment() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub

		mFlipperLayout = (FlipperLayout) findViewById(R.id.trip_card_main_layout);

	}

}
