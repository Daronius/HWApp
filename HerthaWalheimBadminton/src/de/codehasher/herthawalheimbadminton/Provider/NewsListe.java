package de.codehasher.herthawalheimbadminton.Provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class NewsListe {
	public static class Columns implements BaseColumns {
		public static final String NAME = "name";
	}

	public static final String CONTENT_DIRECTORY = "newsliste";
	public static final String TABLE_NAME = "NewsListe";
	public static final Uri CONTENT_URI = Uri.parse("content://" + NewsListeContentProvider.AUTHORITY + "/" + CONTENT_DIRECTORY);
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/de.codehasher.herthawalheimbadminton.newsliste";
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/de.codehasher.herthawalheimbadminton.newsliste";

	public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + Columns._ID + " integer primary key autoincrement," + Columns.NAME
			+ " text " + ")";
}
