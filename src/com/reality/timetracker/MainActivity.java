package com.reality.timetracker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.reality.timetracker.dao.TimeDAO;
import com.reality.timetracker.dao.TimeContract.TimeEntry;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	TimeDAO tDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tDao = new TimeDAO(this);

		SQLiteDatabase db = tDao.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put(TimeEntry.COLUMN_NAME_TIME_GOING_DOWN, "11:30pm");

		values.put(TimeEntry.COLUMN_NAME_GETTING_UP, "8:00am");
		values.put(TimeEntry.COLUMN_NAME_TYPE, "Nap");
		
		db.insert(TimeEntry.TABLE_NAME, null, values);
		
//		db.query(TimeEntry.TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy)

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		// mSectionsPagerAdapter = new SectionsPagerAdapter(
		// getSupportFragmentManager());
		//
		// // Set up the ViewPager with the sections adapter.
		// mViewPager = (ViewPager) findViewById(R.id.pager);
		// mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = new SleepFragment();
				break;
			default:
				fragment = new SleepFragment();
				break;
			}

			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	public static class SleepFragment extends Fragment implements
			OnClickListener {
		// Helpful for use within methods that have a limited view. Allows me to
		// avoid having a variable for everything, can just find by ID
		ViewGroup theContainer = null;

		public SleepFragment() {
		}

		@SuppressLint("SimpleDateFormat")
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			TimeDAO tDao = new TimeDAO(getActivity());

			theContainer = container;

			View rootView = inflater.inflate(R.layout.fragment_sleep,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.sleep_time);

			Button b1 = (Button) rootView.findViewById(R.id.sleep_button);

			Button b2 = (Button) rootView.findViewById(R.id.waking_up_button);

			b1.setOnClickListener(this);
			b2.setOnClickListener(this);

			Calendar cal = Calendar.getInstance();

			SimpleDateFormat format1 = new SimpleDateFormat("MM/dd hh:mm a");
			String date1 = format1.format(cal.getTime());

			dummyTextView.setText(date1);

			// dummyTextView.setText(Integer.toString(getArguments().getInt(
			// ARG_SECTION_NUMBER)));

			return rootView;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.sleep_button: {

				Log.d("Click", "Up recieved");
				TextView textView = (TextView) theContainer
						.findViewById(R.id.sleep_time);
				textView.setText("UP");
				break;
			}
			default: {
				Log.d("Click", "Bed");

				TextView textView = (TextView) theContainer
						.findViewById(R.id.sleep_time);
				textView.setText("BED");
			}
			}
		}
	}

}
