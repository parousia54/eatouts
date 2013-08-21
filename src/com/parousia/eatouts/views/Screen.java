package com.parousia.eatouts.views;

import com.parousia.eatouts.R;
import com.parousia.eatouts.control.EatOutsLocationManager;
import com.parousia.eatouts.settings.SettingsActivity;

public class Screen extends SherlockFragmentActivity{
	
	
	SharedPreferences eatOutsPrefs;
	SharedPreferences.Editor eatOutsPrefsEditor;
	
	private EatOutsLocationManager locationManager;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.landing, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {

		case R.id.action_settings:
			intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_about:
			intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
			return true;
		case android.R.id.home:
			// Intent intent = new Intent(this, OverviewActivity.class);
			// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// startActivity(intent);
			return true;

		}

		return super.onOptionsItemSelected(item);
	}

	public EatOutsLocationManager getLocationManager() {
		return locationManager;
	}
	
	
	
	

}
