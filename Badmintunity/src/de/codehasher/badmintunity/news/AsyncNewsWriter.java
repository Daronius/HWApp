package de.codehasher.badmintunity.news;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;

import de.codehasher.badmintunity.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class AsyncNewsWriter extends AsyncTask<String, Void, String> {
	public static final int NEWS_ADD = 1;
	public static final int NEWS_DELETE = 2;

	private final ProgressDialog dialog;
	private Context context;
	private int action;

	public AsyncNewsWriter(Context context, int action) {
		this.context = context;
		this.action = action;
		dialog = new ProgressDialog(context);
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		dialog.dismiss();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		switch (action) {
		case NEWS_ADD:
			dialog.setMessage(context.getResources().getString(R.string.news_creatingNews));
			break;
		case NEWS_DELETE:
			dialog.setMessage(context.getResources().getString(R.string.news_deletingNews));
			break;
		}
		dialog.show();
	}

	@Override
	protected String doInBackground(String... params) {

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
				// newsId.add(arr.getJSONObject(i).getString("news_id"));
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}
}
