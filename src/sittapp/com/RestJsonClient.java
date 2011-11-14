package sittapp.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class RestJsonClient {
	private static String url = "sittapp.appspot.com";
	
    public static JSONObject connect(String path, List<NameValuePair> qparams) {
        HttpClient httpclient = new DefaultHttpClient();
        
        // Format the query paramters
        URI uri = null;
		try {
			uri = URIUtils.createURI("http", url, -1, path, 
			    URLEncodedUtils.format(qparams, "UTF-8"), null);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
        HttpGet httpget = new HttpGet(uri);

        // Execute the request
        JSONObject json = new JSONObject();
        try {
        	HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                String result= convertStreamToString(instream);
                Log.d("RestJsonClient", path+": "+result);
                json=new JSONObject(result);

                instream.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static JSONArray connectArray(String path, List<NameValuePair> qparams) {
        HttpClient httpclient = new DefaultHttpClient();
        
        // Format the query paramters
        URI uri = null;
		try {
			uri = URIUtils.createURI("http", url, -1, path, 
			    URLEncodedUtils.format(qparams, "UTF-8"), null);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
        HttpGet httpget = new HttpGet(uri);

        // Execute the request
        JSONArray json = new JSONArray();

        try {
        	HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                String result= convertStreamToString(instream);
                Log.d("RestJsonClient", path+": "+result);
                json=new JSONArray(result);
                instream.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    } 
    
    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
