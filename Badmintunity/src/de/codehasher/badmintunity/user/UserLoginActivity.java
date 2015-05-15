package de.codehasher.badmintunity.user;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import de.codehasher.badmintunity.R;

public class UserLoginActivity extends Activity {
	private TextView email;
	private TextView password;
	private Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login_layout);

		email = (TextView) findViewById(R.id.user_login_email);
		password = (TextView) findViewById(R.id.user_login_password);

		login = (Button) findViewById(R.id.user_login_button);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isValidEmail(email.getText()) && isValidPassword(password.getText())) {

					AsyncUserLoader aul = new AsyncUserLoader(UserLoginActivity.this);
					aul.execute("http://www.codehasher.de/rest/api/query.php?q=checkUser&email=" + email.getText() + "&password="
							+ MD5(password.getText().toString()));

					JSONObject obj = null;
					try {
						obj = aul.get();
						if (obj.isNull("Error")) {
							User u = User.getInstance();

							u.setId(obj.getString("id"));
							u.setEmail(email.getText().toString());
							u.setName(obj.getString("name"));
							u.setToken(obj.getString("token"));
							u.setPermission(obj.getString("permission"));
							
							finish();
						} else {
							Toast.makeText(UserLoginActivity.this, getString(R.string.user_login_error_text), Toast.LENGTH_SHORT).show();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					Toast.makeText(UserLoginActivity.this, getString(R.string.user_login_input_error_text), Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	protected boolean isValidEmail(CharSequence email) {
		// ToDo: E-Mail Check
		if (email.length() > 1) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isValidPassword(CharSequence password) {
		// ToDo: Password Check
		if (password.length() > 1) {
			return true;
		} else {
			return false;
		}
	}

	protected String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			Log.e("MD5", sb.toString());
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

}
