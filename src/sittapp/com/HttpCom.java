package sittapp.com;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import sittapp.com.RestJsonClient;

public class HttpCom {
	String url = "http://sittapp.appspot.com/sittapp_backend";
	String LOG_TAG = "HttpCom";
	
	public void ajaxTest() {
		JSONObject jsoncli = RestJsonClient.connect(url);
		Log.d(LOG_TAG, "INC PRINT");
		try {
			Log.d(LOG_TAG, jsoncli.getString("key1"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
