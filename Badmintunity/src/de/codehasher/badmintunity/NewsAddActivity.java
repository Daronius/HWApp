package de.codehasher.badmintunity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import de.codehasher.badmintunity.news.AsyncNewsWriter;

public class NewsAddActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_add_layout);
	}

	@Override
	protected void onStart() {
		super.onStart();
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater infalter = getMenuInflater();
		infalter.inflate(R.menu.news_add_actionbarmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.itemSafeNews:
			EditText newsTopic = (EditText) findViewById(R.id.editTextTopic);
			EditText newsContent = (EditText) findViewById(R.id.editTextContent);
			String newsTopicURL = "";
			String newsContentURL = "";
			try {
				newsTopicURL = URLEncoder.encode(newsTopic.getText().toString(), "utf-8");
				newsContentURL = URLEncoder.encode(newsContent.getText().toString(), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (newsTopicURL.length() <= 0) {
				Toast.makeText(this, "News is empty", Toast.LENGTH_SHORT).show();
			} else if (newsContentURL.length() <= 0) {
				Toast.makeText(this, "Content is empty", Toast.LENGTH_SHORT).show();
			} else {
				AsyncNewsWriter anw = new AsyncNewsWriter(this, AsyncNewsWriter.NEWS_ADD);
				anw.execute("http://www.codehasher.de/rest/api/query.php?q=addnews&topic=" + newsTopicURL + "&content=" + newsContentURL + "");
				NavUtils.navigateUpFromSameTask(this);
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
