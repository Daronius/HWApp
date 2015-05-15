package de.codehasher.badmintunity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import de.codehasher.badmintunity.news.AsyncListViewLoader;
import de.codehasher.badmintunity.news.NewsAddActivity;
import de.codehasher.badmintunity.user.User;
import de.codehasher.badmintunity.user.UserLoginActivity;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart() {
		super.onStart();
		getActionBar().setDisplayHomeAsUpEnabled(true);

		if(!User.getInstance().isUserLoggedIn()){
			Intent loginUserIntent = new Intent(this, UserLoginActivity.class);
			startActivity(loginUserIntent);	
		}

		registerForContextMenu((ListView) findViewById(R.id.listViewNews));
		getNews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.news_actionbarmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show();
			break;
		case R.id.itemRefreshNews:
			getNews();
			break;
		case R.id.itemAddNews:
			Intent addNewsIntent = new Intent(this, NewsAddActivity.class);
			startActivity(addNewsIntent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.news_contextmenu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.itemDeleteNews:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
			TextView v = (TextView) info.targetView.findViewById(R.id.textViewNewsId);
			AsyncListViewLoader alvl = new AsyncListViewLoader(this);
			alvl.execute("http://www.codehasher.de/rest/api/query.php?q=deletenews&id=" + v.getText());
			getNews();
			break;
		}
		return super.onContextItemSelected(item);
	}

	private void getNews() {
		AsyncListViewLoader alvl = new AsyncListViewLoader(this);
		alvl.execute("http://www.codehasher.de/rest/api/query.php?q=getnews");
		ListView lView = (ListView) findViewById(R.id.listViewNews);
		lView.setAdapter(alvl.getAdpt());
	}
}
