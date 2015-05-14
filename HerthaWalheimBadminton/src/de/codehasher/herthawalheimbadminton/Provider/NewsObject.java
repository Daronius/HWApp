package de.codehasher.herthawalheimbadminton.Provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class NewsObject {
	public static class Columns implements BaseColumns {
		public static final String LISTE = "liste";
		public static final String EINTRAG = "eintrag";
		public static final String SORTIERUNG = "sortierung";
	}

	public static final String CONTENT_DIRECTORY = "newsobject";
	public static final String TABLE_NAME = "NewsObject";
	public static final Uri CONTENT_URI = Uri.parse("content://" + NewsListeContentProvider.AUTHORITY + "/" + CONTENT_DIRECTORY);
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/de.codehasher.herthawalheimbadminton.newsobject";
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/de.codehasher.herthawalheimbadminton.newsobject";

	public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + Columns._ID + " integer primary key autoincrement," + Columns.LISTE
			+ " integer," + Columns.EINTRAG + " text " + ")";

}
