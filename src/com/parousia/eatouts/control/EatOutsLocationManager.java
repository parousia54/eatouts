package com.parousia.eatouts.control;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.parousia.eatouts.R;
import com.parousia.eatouts.util.LocationUtils;
import com.parousia.eatouts.views.Screen;

public class EatOutsLocationManager implements LocationListener,
		GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener {

	private LocationRequest mLocationRequest;
	private LocationClient mLocationClient;
	boolean mUpdatesRequested = false;
	private Context context;

	public EatOutsLocationManager(Context context) {
		this.context = context;
		init();
	}

	public LocationClient init() {

		mLocationRequest = LocationRequest.create();
		mLocationRequest
				.setInterval(LocationUtils.UPDATE_INTERVAL_IN_MILLISECONDS);
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

		mLocationRequest
				.setFastestInterval(LocationUtils.FAST_INTERVAL_CEILING_IN_MILLISECONDS);
		mUpdatesRequested = false;

		mLocationClient = new LocationClient(context, this, this);

		return mLocationClient;

	}

	private boolean servicesConnected() {

		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(context);

		if (ConnectionResult.SUCCESS == resultCode) {
			Log.d(LocationUtils.APPTAG,
					context.getString(R.string.play_services_available));

			return true;
		} else {
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode,
					(Activity) context, 0);
			if (dialog != null) {
				ErrorDialogFragment errorFragment = new ErrorDialogFragment();
				errorFragment.setDialog(dialog);
				errorFragment.show(((FragmentActivity) context)
						.getSupportFragmentManager(), LocationUtils.APPTAG);
			}
			return false;
		}
	}

	public Location getLocation(View v) {

		if (servicesConnected()) {
			return mLocationClient.getLastLocation();
		}
		return null;
	}

	// For Eclipse with ADT, suppress warnings about Geocoder.isPresent()
	@SuppressLint("NewApi")
	public String getAddress(View v) {

		// In Gingerbread and later, use Geocoder.isPresent() to see if a
		// geocoder is available.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD
				&& !Geocoder.isPresent()) {
			Toast.makeText(context, R.string.no_geocoder_available,
					Toast.LENGTH_LONG).show();
			return LocationUtils.EMPTY_STRING;
		}

		if (servicesConnected()) {
			Location currentLocation = mLocationClient.getLastLocation();

			try {
				return (new EatOutsLocationManager.GetAddressTask(context))
						.execute(currentLocation).get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return LocationUtils.EMPTY_STRING;
	}

	public void startUpdates(View v) {
		mUpdatesRequested = true;

		if (servicesConnected()) {
			startPeriodicUpdates();
		}
	}

	public void stopUpdates(View v) {
		mUpdatesRequested = false;

		if (servicesConnected()) {
			stopPeriodicUpdates();
		}
	}

	@Override
	public void onConnected(Bundle bundle) {
		if (mUpdatesRequested) {
			startPeriodicUpdates();
		}
	}

	@Override
	public void onDisconnected() {
	}

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {

		if (connectionResult.hasResolution()) {
			try {

				connectionResult.startResolutionForResult((Activity) context,
						LocationUtils.CONNECTION_FAILURE_RESOLUTION_REQUEST);

			} catch (IntentSender.SendIntentException e) {

				e.printStackTrace();
			}
		} else {

			showErrorDialog(connectionResult.getErrorCode());
		}
	}

	@Override
	public void onLocationChanged(Location location) {
	}

	private void startPeriodicUpdates() {

		mLocationClient.requestLocationUpdates(mLocationRequest, this);
	}

	private void stopPeriodicUpdates() {
		mLocationClient.removeLocationUpdates(this);
	}

	protected class GetAddressTask extends AsyncTask<Location, Void, String> {

		Context localContext;

		public GetAddressTask(Context context) {

			super();

			localContext = context;
		}

		@Override
		protected String doInBackground(Location... params) {
			Geocoder geocoder = new Geocoder(localContext, Locale.getDefault());

			Location location = params[0];

			List<Address> addresses = null;

			try {

				addresses = geocoder.getFromLocation(location.getLatitude(),
						location.getLongitude(), 1);
				Log.d(LocationUtils.APPTAG,
						"No of address = " + addresses.size());
			} catch (IOException exception1) {

				Log.e(LocationUtils.APPTAG, context
						.getString(R.string.IO_Exception_getFromLocation));

				exception1.printStackTrace();

				return (context
						.getString(R.string.IO_Exception_getFromLocation));
			} catch (IllegalArgumentException exception2) {

				String errorString = context.getString(
						R.string.illegal_argument_exception,
						location.getLatitude(), location.getLongitude());
				Log.e(LocationUtils.APPTAG, errorString);
				exception2.printStackTrace();
				return errorString;
			}
			if (addresses != null && addresses.size() > 0) {

				Address address = addresses.get(0);

				String addressText = context.getString(
						R.string.address_output_string,

						address.getMaxAddressLineIndex() > 0 ? address
								.getAddressLine(0) : "", address.getLocality(),

						address.getCountryName());

				Log.d(LocationUtils.APPTAG, addressText);
				return addressText;
			} else {
				return context.getString(R.string.no_address_found);
			}
		}

		@Override
		protected void onPostExecute(String address) {
		}
	}

	private void showErrorDialog(int errorCode) {

		Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(errorCode,
				(Activity) context,
				LocationUtils.CONNECTION_FAILURE_RESOLUTION_REQUEST);

		if (errorDialog != null) {

			ErrorDialogFragment errorFragment = new ErrorDialogFragment();

			errorFragment.setDialog(errorDialog);

			errorFragment.show(
					((FragmentActivity) context).getSupportFragmentManager(),
					LocationUtils.APPTAG);
		}
	}

	public static String getLatLng(Context context, Location currentLocation) {
		if (currentLocation != null) {

			return context.getString(R.string.latitude_longitude,
					currentLocation.getLatitude(),
					currentLocation.getLongitude());
		} else {

			return LocationUtils.EMPTY_STRING;
		}
	}

	public static class ErrorDialogFragment extends DialogFragment {

		private Dialog mDialog;

		public ErrorDialogFragment() {
			super();
			mDialog = null;
		}

		public void setDialog(Dialog dialog) {
			mDialog = dialog;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return mDialog;
		}
	}
}
