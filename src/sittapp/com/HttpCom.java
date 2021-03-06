package sittapp.com;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import sittapp.com.RestJsonClient;
import sittapp.model.*;

public class HttpCom {
	final static String TAG = "HttpCom";
	
	/**
	 * Soft method to log in with user
	 * @param username Username of user that is logging in.
	 * @return the user data in a User object.
	 */
	public User login(String username) {
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("username", username));
        JSONObject json = RestJsonClient.connect("/login", qparams);
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
			}
			// Gang invites data
			JSONArray jsonGangInvs = json.getJSONArray("ganginvobjs");
			for (int i = 0; i<jsonGangInvs.length(); i++) {
				JSONObject jsong = jsonGangInvs.getJSONObject(i);
				Gang gang = JSONtoGang(jsong);
				user.addGangInvite(gang);
			}
			return user;
		} catch (JSONException e) {
			Log.i(TAG, "Failed to parse JSON data");
		}
		return null;
	}
	
	/**
	 * 
	 * @param username Username of current application user
	 * @param gangName Desired name on gang.
	 * @return (Bool) If action was successful.
	 */
	public Gang gangCreate(String username, String gangName) {
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("username", username));
        qparams.add(new BasicNameValuePair("gangname", gangName));
        JSONObject json = RestJsonClient.connect("/gangcreate", qparams);
        return JSONtoGang(json);
	}
	
	/**
	 * 
	 * @param gangId Id of gang a user should be invited to
	 * @param username Username of user to be invited
	 * @return (Bool) If action was successful.
	 */
	public boolean gangInvite(Long gangId, String username) {
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("gangid", ""+gangId));
        qparams.add(new BasicNameValuePair("username", ""+username));
        JSONObject json = RestJsonClient.connect("/ganginvite", qparams);
        return checkIfOk(json, "gangInvite");
	}
	
	/**
	 * 
	 * @param gangId Id of gang user are accepting invitation to.
	 * @param username Username for user accepting the invitation
	 * @return (Bool) If action was successful.
	 */
	public boolean gangAccept(Long gangId, String username) {
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("gangid", ""+gangId));
        qparams.add(new BasicNameValuePair("username", ""+username));
        JSONObject json = RestJsonClient.connect("/gangaccept", qparams);
        return checkIfOk(json, "gangAccept");
	}
	
	/**
	 * 
	 * @param gangId Id of gang user are declining invitation to.
	 * @param username User's username.
	 * @return (Bool) If action was successful.
	 */
	public boolean gangDecline(Long gangId, String username) {
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("gangid", ""+gangId));
        qparams.add(new BasicNameValuePair("username", ""+username));
        JSONObject json = RestJsonClient.connect("/gangdecline", qparams);
        return checkIfOk(json, "gangDecline");
	}
	
	/**
	 * 
	 * @param gangId Id of gang user are leaving.
	 * @param username User's username.
	 * @return (Bool) If action was successful.
	 */
	public boolean gangLeave(Long gangId, String username) {
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("gangid", ""+gangId));
        qparams.add(new BasicNameValuePair("username", ""+username));
        JSONObject json = RestJsonClient.connect("/gangleave", qparams);
        return checkIfOk(json, "gangLeave");
	}
	
	public ArrayList<User> getUserList() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		JSONArray jsonArr = RestJsonClient.connectArray("/userlist", qparams);
		ArrayList<User> users = new ArrayList<User>();
		try {
			for (int i = 0; i<jsonArr.length(); i++) {
				String name = jsonArr.getJSONObject(i).getString("name");
				users.add(new User(name));
			}
		} catch (JSONException e1) {
			Log.i(TAG, "Failed to load userlist");
			Log.d(TAG, e1.toString());
		}
		return users;
	}
	
	// ******** PRIVATE INTERNAL METHODS ********
	// ******************************************
	
	private Gang JSONtoGang(JSONObject json) {
		try {
			Gang gang = new Gang(json.getLong("id"), json.getString("name"));
			JSONArray jsonGangUsers = json.getJSONArray("users");
			for (int y = 0; y<jsonGangUsers.length(); y++) {
				String name = (String) jsonGangUsers.get(y);
				gang.addMember(new GangMember(name));
			}
			return gang;
		} catch (JSONException e) {
			Log.i(TAG, "Failed to parse JSON data");
			return null;
		}
	}
	
	private boolean checkIfOk(JSONObject json, String msgTag) {
        String msg = null;
		try {
			msg = json.getString("MSG");
	        if (msg.equals("OK")) {
	        	Log.d(TAG, msgTag+" - success");
	        	return true;
	        }
	        else {
	        	Log.d(TAG, msgTag+" - failure");
	        	return false;
	        }
		} catch (JSONException e) {
			Log.i(TAG, "Failed to parse JSON data");
			return false;
		}
	}
}
