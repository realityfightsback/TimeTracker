package com.reality.timetracker.dao;

import android.provider.BaseColumns;

public class TimeContract {

	public static abstract class TimeEntry implements BaseColumns {
		public static final String TABLE_NAME = "sleep";
		public static final String COLUMN_NAME_TIME_GOING_DOWN = "timegoingdown";
		public static final String COLUMN_NAME_TYPE = "type";
		public static final String COLUMN_NAME_GETTING_UP = "timeup";

		public static final String TEXT_TYPE = " TEXT";
		public static final String COMMA_SEP = ",";
		public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
				+ TimeEntry.TABLE_NAME + " (" + TimeEntry._ID
				+ " INTEGER PRIMARY KEY,"
				+ TimeEntry.COLUMN_NAME_TIME_GOING_DOWN + TEXT_TYPE + COMMA_SEP
				+ TimeEntry.COLUMN_NAME_TYPE + TEXT_TYPE + COMMA_SEP
				+ TimeEntry.COLUMN_NAME_GETTING_UP + TEXT_TYPE + "  )";
		public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
				+ TimeEntry.TABLE_NAME;

	}

}
