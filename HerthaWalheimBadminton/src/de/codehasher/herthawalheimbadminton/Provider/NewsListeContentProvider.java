package de.codehasher.herthawalheimbadminton.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class NewsListeContentProvider extends ContentProvider {

	public static final int DB_VERSION = 1;
	public static final String DATABASE_NAME = "HertheWalheimBadminton.db";
	public static final String AUTHORITY = "de.codehasher.herthawalheimbadminton";

	private static final int NEWSLISTE_DIRECTORY = 1;
	private static final int NEWSLISTE_ITEM = 2;

	private static final int NEWSOBJECT_DIRECTORY = 3;
	private static final int NEWSOBJECT_ITEM = 4;

	private static final int NEWSLISTE_NEWSOBJECT_DIRECTORY = 5;

	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(AUTHORITY, NewsListe.CONTENT_DIRECTORY, NEWSLISTE_DIRECTORY);
		sURIMatcher.addURI(AUTHORITY, NewsListe.CONTENT_DIRECTORY + "/#", NEWSLISTE_ITEM);

		sURIMatcher.addURI(AUTHORITY, NewsObject.CONTENT_DIRECTORY, NEWSOBJECT_DIRECTORY);
		sURIMatcher.addURI(AUTHORITY, NewsObject.CONTENT_DIRECTORY + "/#", NEWSOBJECT_ITEM);

		sURIMatcher.addURI(AUTHORITY, NewsListe.CONTENT_DIRECTORY + "/#/" + NewsListe.CONTENT_DIRECTORY, NEWSLISTE_NEWSOBJECT_DIRECTORY);
	}

	private static class herthawalheimbadmintonDatabaseHelper extends SQLiteOpenHelper {

		public herthawalheimbadmintonDatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(NewsListe.CREATE_TABLE);
			db.execSQL(NewsObject.CREATE_TABLE);
			createSamples(db);
		}

		private void createSamples(SQLiteDatabase db) {
			String ssql = "INSERT INTO " + NewsListe.TABLE_NAME + "(" + NewsListe.Columns.NAME + ") values (?)";
			db.execSQL(ssql, new String[] { "Einkaufen" });
			db.execSQL(ssql, new String[] { "ToDo" });
			db.execSQL(ssql, new String[] { "Wunschzettel" });
			db.execSQL(ssql, new String[] { "Lieblingsbücher" });
			db.execSQL(ssql, new String[] { "Weine" });
			db.execSQL(ssql, new String[] { "Rezepte" });
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

	}

	private herthawalheimbadmintonDatabaseHelper dbhelper;

	@Override
	public boolean onCreate() {
		dbhelper = new herthawalheimbadmintonDatabaseHelper(getContext());
		return true;
	}

	@Override
	public String getType(Uri uri) {
		switch (sURIMatcher.match(uri)) {
		case NEWSLISTE_DIRECTORY:
			return NewsListe.CONTENT_TYPE;
		case NEWSLISTE_ITEM:
			return NewsListe.CONTENT_ITEM_TYPE;
		case NEWSOBJECT_DIRECTORY:
			return NewsObject.CONTENT_TYPE;
		case NEWSOBJECT_ITEM:
			return NewsObject.CONTENT_ITEM_TYPE;
		case NEWSLISTE_NEWSOBJECT_DIRECTORY:
			return NewsObject.CONTENT_ITEM_TYPE;
		}
		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		switch (sURIMatcher.match(uri)) {
		case NEWSLISTE_DIRECTORY:
			return dbhelper.getReadableDatabase().query(NewsListe.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
		case NEWSLISTE_ITEM:
			break;
		case NEWSOBJECT_DIRECTORY:
			return dbhelper.getReadableDatabase().query(NewsObject.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
		case NEWSOBJECT_ITEM:
			break;
		case NEWSLISTE_NEWSOBJECT_DIRECTORY:
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri.toString());
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		switch (sURIMatcher.match(uri)) {
		case NEWSLISTE_DIRECTORY:
			break;
		case NEWSLISTE_ITEM:
			break;
		case NEWSOBJECT_DIRECTORY:
			break;
		case NEWSOBJECT_ITEM:
			break;
		case NEWSLISTE_NEWSOBJECT_DIRECTORY:
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri.toString());
		}
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		switch (sURIMatcher.match(uri)) {
		case NEWSLISTE_DIRECTORY:
			break;
		case NEWSLISTE_ITEM:
			break;
		case NEWSOBJECT_DIRECTORY:
			break;
		case NEWSOBJECT_ITEM:
			break;
		case NEWSLISTE_NEWSOBJECT_DIRECTORY:
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri.toString());
		}
		return 0;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		switch (sURIMatcher.match(uri)) {
		case NEWSLISTE_DIRECTORY:
			break;
		case NEWSLISTE_ITEM:
			break;
		case NEWSOBJECT_DIRECTORY:
			break;
		case NEWSOBJECT_ITEM:
			break;
		case NEWSLISTE_NEWSOBJECT_DIRECTORY:
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri.toString());
		}
		return 0;
	}

}
