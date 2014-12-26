package com.m.tripcard.ui.fragment;

import com.m.tripcard.R;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class RightMainFragment extends BaseFragment implements
		OnItemClickListener {

	private Activity mContext;

	private TextView mVersion;

	private RelativeLayout parent;

	private ListView menuItems;

	private BaseAdapter mBaseAdapter;

	private AbsListView.LayoutParams lp;

	int[] icons = { R.drawable.icon_menu_setting,
			R.drawable.icon_menu_feedback,
			R.drawable.icon_menu_binding_account,
			R.drawable.icon_menu_check_for_update, R.drawable.icon_menu_about };

	int[] titles = { R.string.menu_setting_title, R.string.menu_feedback_title,
			R.string.menu_binding_account_title,
			R.string.menu_check_for_updates_title, R.string.menu_about_title };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mContext = getActivity();

		initViews();

		initAdapter();

		menuItems.setAdapter(mBaseAdapter);

		menuItems.setOnItemClickListener(this);

		return parent;

	}

	private void initViews() {

		parent = (RelativeLayout) RelativeLayout.inflate(getActivity(),
				R.layout.trip_card_right, null);

		mVersion = (TextView) parent.findViewById(R.id.menu_app_version);

		menuItems = (ListView) parent.findViewById(R.id.menu_items);

		lp = new AbsListView.LayoutParams(
				AbsListView.LayoutParams.MATCH_PARENT, getActivity()
						.getResources().getDimensionPixelSize(
								R.dimen.menu_item_height));

		menuItems.setAdapter(mBaseAdapter);

		mVersion.setText(getVersion());
	}

	public String getVersion() {
		try {
			PackageManager manager = this.getActivity().getPackageManager();
			PackageInfo info = manager.getPackageInfo(
					mContext.getPackageName(), 0);
			String version = info.versionName;
			return mContext.getString(R.string.version_info_title) + version;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		// TODO Auto-generated method stub

	}
}
