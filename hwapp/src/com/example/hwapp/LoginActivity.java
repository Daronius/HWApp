package com.example.hwapp;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class LoginActivity extends Activity {

	private Config config = Config.getInstance();
	private TextView email;
	private TextView password;
	private Button login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		email = (TextView) findViewById(R.id.email);
		password = (TextView) findViewById(R.id.password);
		
		login = (Button) findViewById(R.id.login_button);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isValidEmail(email.getText()) && isValidPassword(password.getText())) {
					JSONObject jArray = null;
					try {
						JSON json = new JSON();
						json.execute(config.getValue("API_LINK") + "q=checkUser&email="+ email.getText() +"&password="+ MD5(password.getText().toString()));
						jArray = json.get();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();	
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
										
					Intent returnIntent = new Intent();

					if(jArray.length() > 0) {
						try {
							returnIntent.putExtra("token", jArray.getString("token"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						setResult(RESULT_OK,returnIntent);
					} else {
						returnIntent.putExtra("token","-1");
						setResult(RESULT_CANCELED, returnIntent);
					}
					finish();
					
				} else {
					Toast.makeText(LoginActivity.this, getString(R.string.login_input_error), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	protected boolean isValidEmail(CharSequence email) {
		//ToDo: E-Mail Check
		if(email.length() > 1) {
			return true;
		} else { 
			return false;
		}
	}
	
	protected boolean isValidPassword(CharSequence password) {
		//ToDo: Password Check
		if(password.length() > 1) {
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
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	       }
	        Log.e("MD5", sb.toString());
	        return sb.toString();
	    } catch (java.security.NoSuchAlgorithmException e) {
	    }
	    return null;
	}
}
