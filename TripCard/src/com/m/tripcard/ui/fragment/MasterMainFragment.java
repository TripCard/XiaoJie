package com.m.tripcard.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import com.m.tripcard.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MasterMainFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ListView list = new ListView(getActivity());
		list.setAdapter(new BaseAdapter() {

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				// TODO Auto-generated method stub
				TextView text = new TextView(getActivity());
				text.setText(arg0 + "");
				return text;
			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 20;
			}
		});
		list.setBackgroundColor(getResources().getColor(R.color.main_bg));
		return list;

	}
}
