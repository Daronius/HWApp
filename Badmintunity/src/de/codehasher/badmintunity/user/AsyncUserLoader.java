package de.codehasher.badmintunity.user;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import de.codehasher.badmintunity.R;

public class AsyncUserLoader extends AsyncTask<String, Void, JSONObject> {

	private final ProgressDialog dialog;
	private Context context;

	public AsyncUserLoader(Context context) {
		this.context = context;
		dialog = new ProgressDialog(context);
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);
		dialog.dismiss();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog.setMessage(context.getResources().getString(R.string.user_login_login_text));
		dialog.show();
	}

	@Override
	protected JSONObject doInBackground(String... params) {
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
			return new JSONObject(JSONResp);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}
}
