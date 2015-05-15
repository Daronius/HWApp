package de.codehasher.badmintunity.user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class User {

	private static User user;

	private int id = -1;
	private String name = "";
	private String email = "";
	private String token = "";
	private String permission = "";

	private User() {

	}

	public static User getInstance() {
		if (user == null) {
			user = new User();
		}
		return user;
	}

	public boolean isUserLoggedIn() {
		if (id > 0 && email != "" && token != "") {
			return true;
		} else {
			return false;
		}
	}

	public String getPermission() {
		return permission;
	}

	public String getPermission(String key) {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;

		try {
			JSONArray jArray = new JSONArray(permission);

			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json_data = jArray.getJSONObject(i);

				Log.i("log_tag", "Name: " + json_data.getString("name"));
				Log.i("log_tag", "Read: " + json_data.getString("read"));
				Log.i("log_tag", "Write: " + json_data.getString("write"));
				Log.i("log_tag", "Delete: " + json_data.getString("delete"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(String string) {
		this.id = Integer.valueOf(string);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
