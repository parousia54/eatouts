package com.parousia.eatouts.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.parousia.eatouts.data.BaseResponse;
import com.parousia.eatouts.data.GooglePlacesAPIResponse;
import com.parousia.eatouts.data.constants.ErrorConstants;
import com.parousia.eatouts.util.LocationUtils;

public class NetworkManager {

	private int NETWORK_READ_TIMEOUT = 10000;
	private int NETWORK_CONNECTION_TIMEOUT = 15000;
	private String NETWORK_REQUEST_METHOD_GET = "GET";
	public final int GOOGLE_PLACES = 1;
	public static String GOOGLE_PLACES_DOMAIN_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";

	String jsonReturn;
	ProgressDialog progressDialog;
	
	Gson gson;

	public BaseResponse connect(String URL, Context context, int source) {
		gson = new Gson();
		progressDialog = new ProgressDialog(context,
				ProgressDialog.STYLE_SPINNER);
		progressDialog.setTitle("Doing some network stuff!");
		BaseResponse response;
		if (hasNetworkConnection(context)) {
			new NetworkTask().execute(URL);
			switch (source) {
			case GOOGLE_PLACES:
				response = new GooglePlacesAPIResponse();
				response = gson.fromJson(jsonReturn, GooglePlacesAPIResponse.class);
				response.setError(ErrorConstants.NO_ERROR);
			default:
				response = new BaseResponse();
				response.setError(ErrorConstants.INVALID_SOURCE);
			}
		} else {
			response = new BaseResponse();
			response.setError(ErrorConstants.NO_NETWORK_ERROR);
		}
		return response;
	}

	public static boolean hasNetworkConnection(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}

	private class NetworkTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... urls) {
			try {
				return doNetworkQuery(urls[0]);
			} catch (IOException e) {
				return null;
			}
		}

		@Override
		protected void onPostExecute(String result) {
			jsonReturn = result;
			if (progressDialog.isShowing()) {
				progressDialog.dismiss();
			}

		}

	}

	public String doNetworkQuery(String urlString) throws IOException {

		InputStream is = null;
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(NETWORK_READ_TIMEOUT);
			conn.setConnectTimeout(NETWORK_CONNECTION_TIMEOUT);
			conn.setRequestMethod(NETWORK_REQUEST_METHOD_GET);
			conn.setDoInput(true);
			conn.connect();

			int responseCode = conn.getResponseCode();
			Log.d(LocationUtils.APPTAG, "The response is: " + responseCode);
			is = conn.getInputStream();
			String contentAsString = readIt(is);
			return contentAsString;
		} finally {
			if (is != null) {
				is.close();
			}
		}

	}

	public String readIt(InputStream stream) throws IOException,
			UnsupportedEncodingException {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		reader = new BufferedReader(new InputStreamReader(stream));
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		return builder.toString();
	}

}
