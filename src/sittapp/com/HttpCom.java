package sittapp.com;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import sittapp.com.RestJsonClient;
import sittapp.model.*;

public class HttpCom {
	final static String LOG_TAG = "HttpCom";
	
	public User login(String name) {
		//JSONObject jsoncli = RestJsonClient.connect(url);
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("name", name));
        
        //String result = "{'name':'Andriod','gangs':[1],'gangInvites':[2,3],'gangobjs':[{'id':1,'name':'Datagutta','users':['Andriod']}],'ganginvobjs':[{'id':2,'name':'Datagutta','users':[]},{'id':3,'name':'Dudes','users':[]}]}";
        JSONObject json = RestJsonClient.connect("/login", qparams);
        /*
		try {
			json = new JSONObject(result);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		Log.d(LOG_TAG, "INC LOGGIN RES");
		Log.d("JSON_DATA", json.toString());
		try {
			// Converting JSON to java objects.
			// User data
			User user = new User(json.getString("name"));
			// Gangs data
			JSONArray jsonGangs = json.getJSONArray("gangobjs");
			for (int i = 0; i<jsonGangs.length(); i++) {
				JSONObject jsong = jsonGangs.getJSONObject(i);
				Gang gang = JSONtoGang(jsong);
				user.addGang(gang);
				Log.d(LOG_TAG, jsong.toString());
			}
			// Gang invites data
			JSONArray jsonGangInvs = json.getJSONArray("ganginvobjs");
			for (int i = 0; i<jsonGangInvs.length(); i++) {
				JSONObject jsong = jsonGangInvs.getJSONObject(i);
				Gang gang = JSONtoGang(jsong);
				user.addGangInvite(gang);
				Log.d(LOG_TAG, jsong.toString());
			}
			return user;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private Gang JSONtoGang(JSONObject json) {
		Gang gang = null;
		try {
			gang = new Gang(json.getLong("id"), json.getString("name"));
			JSONArray jsonGangUsers = json.getJSONArray("users");
			for (int y = 0; y<jsonGangUsers.length(); y++) {
				String name = (String) jsonGangUsers.get(y);
				Log.d(LOG_TAG, "name gotten: "+ name);
				gang.addMember(new GangMember(name));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gang;
	}
}
