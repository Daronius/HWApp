package de.codehasher.badmintunity.news;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.codehasher.badmintunity.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class AsyncListViewLoader extends AsyncTask<String, Void, List<News>> {
	private final ProgressDialog dialog;
	private NewsAdapter adpt;
	private Context context;
	
	public NewsAdapter getAdpt() {
		return adpt;
	}

	public AsyncListViewLoader(Context context) {
		this.context = context;
		dialog = new ProgressDialog(context);
		adpt = new NewsAdapter(new ArrayList<News>(), context);
	}
	
	@Override
	protected void onPostExecute(List<News> result) {
		super.onPostExecute(result);
		dialog.dismiss();
		adpt.setItemList(result);
		adpt.notifyDataSetChanged();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog.setMessage(context.getResources().getString(R.string.news_loadingNews));
		dialog.show();
	}

	@Override
	protected List<News> doInBackground(String... params) {
		List<News> result = new ArrayList<News>();

		try {
			URL u = new URL(params[0]);

			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");

			conn.connect();
			InputStream is = conn.getInputStream();

			// Read the stream
			byte[] b = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			while (is.read(b) != -1)
				baos.write(b);

			String JSONResp = new String(baos.toByteArray());

			JSONArray arr = new JSONArray(JSONResp);
			for (int i = 0; i < arr.length(); i++) {
				result.add(convertNews(arr.getJSONObject(i)));
			}

			return result;
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	private News convertNews(JSONObject obj) throws JSONException {
		String id = obj.getString("news_id");
		String content = obj.getString("news_content");
		String topic = obj.getString("news_topic");
		String date = obj.getString("news_date");

		return new News(id, content, topic, date);
	}

}

