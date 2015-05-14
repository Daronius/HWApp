package com.example.hwapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class JSON extends AsyncTask<String, Void, JSONObject>{
   
	private String result = "";
	
	@Override
	protected JSONObject doInBackground(String... params) {
		
		String url = params[0];
	    InputStream is = null;
		JSONObject jArray = null;
	    HttpURLConnection con = null ;
	    
		try {
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            
            is = con.getInputStream();
             
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            this.result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result: " + e.toString());
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
        
        try {
            jArray = new JSONObject(this.result);
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data: " + e.toString());
        }
        
        return jArray;
	}
	
	protected void onPostExecute() {
        // TODO: check this.exception 
    }
}