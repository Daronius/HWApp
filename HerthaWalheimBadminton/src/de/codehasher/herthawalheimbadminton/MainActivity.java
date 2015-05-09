package de.codehasher.herthawalheimbadminton;

import de.codehasher.herthawalheimbadminton.Provider.NewsListe;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Cursor cursor = this.getContentResolver().query(NewsListe.CONTENT_URI, new String[] { NewsListe.Columns._ID, NewsListe.Columns.NAME }, null, null, NewsListe.Columns.NAME);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[] { NewsListe.Columns.NAME }, new int[] { android.R.id.text1 }, 0);
		
		this.setListAdapter(adapter);
	}
}
