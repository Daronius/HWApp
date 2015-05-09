package de.codehasher.herthawalheimbadminton;

import de.codehasher.herthawalheimbadminton.Provider.NewsListe;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ListActivity implements OnClickListener {

	EditText editTextName;
	Button buttonCreate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		View header = getLayoutInflater().inflate(R.layout.neue_liste_layout, null);
		getListView().addHeaderView(header);
		
		Cursor cursor = this.getContentResolver().query(NewsListe.CONTENT_URI, new String[] { NewsListe.Columns._ID, NewsListe.Columns.NAME }, null, null, NewsListe.Columns.NAME);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[] { NewsListe.Columns.NAME }, new int[] { android.R.id.text1 }, 0);
		
		this.setListAdapter(adapter);
		
		editTextName = (EditText)header.findViewById(R.id.editTextName);
		buttonCreate = (Button)header.findViewById(R.id.buttonCreate);
		
		buttonCreate.setOnClickListener(this);
		
		this.registerForContextMenu(getListView());
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.buttonCreate) {
			createNewList();
		}
	}

	private void createNewList() {
		String name = editTextName.getText().toString();
		ContentResolver cr = getContentResolver();
		ContentValues values = new ContentValues();
		values.put(NewsListe.Columns.NAME, name);
		Uri newNews = cr.insert(NewsListe.CONTENT_URI, values);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		getMenuInflater().inflate(R.menu.newscontextmenu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.itemNewsEdit){
			newsEdit();
		}
		if(item.getItemId() == R.id.itemNewsDelete){
			newsDelete();
		}
		return super.onContextItemSelected(item);
	}

	private void newsEdit() {
		// TODO Auto-generated method stub
		
	}

	private void newsDelete() {
		// TODO Auto-generated method stub
		
	}

}
