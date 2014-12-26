package com.m.tripcard.ui.fragment;

import com.m.tripcard.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LeftMainFragment extends BaseFragment implements
		OnItemClickListener {

	private ListView menuItems;

	private BaseAdapter mBaseAdapter;

	private AbsListView.LayoutParams lp;

	int[] icons = { R.drawable.icon_menu_my_collection,
			R.drawable.icon_menu_my_footprints,
			R.drawable.icon_menu_cancellation_landing };

	int[] titles = { R.string.menu_my_collection_title,
			R.string.menu_my_footprints_title,
			R.string.menu_cancellation_landing_title };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		RelativeLayout parent = (RelativeLayout) RelativeLayout.inflate(
				getActivity(), R.layout.trip_card_left, null);

		menuItems = (ListView) parent.findViewById(R.id.menu_items);

		lp = new AbsListView.LayoutParams(
				AbsListView.LayoutParams.MATCH_PARENT, getActivity()
						.getResources().getDimensionPixelSize(
								R.dimen.menu_item_height));

		initAdapter();

		menuItems.setAdapter(mBaseAdapter);

		menuItems.setOnItemClickListener(this);

		return parent;

	}

	private void initAdapter() {
		mBaseAdapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				RelativeLayout main = (RelativeLayout) RelativeLayout.inflate(
						getActivity(), R.layout.menu_item, null);

				ImageView icon = (ImageView) main.findViewById(R.id.menu_icon);
				TextView title = (TextView) main.findViewById(R.id.menu_title);

				icon.setBackgroundResource(icons[position]);
				title.setText(titles[position]);

				main.setLayoutParams(lp);

				return main;
			}

			@Override
			public long getItemId(int position) {
				return 0;
			}

			@Override
			public Object getItem(int position) {
				return null;
			}

			@Override
			public int getCount() {
				return icons.length;
			}
		};
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {

	}
}
