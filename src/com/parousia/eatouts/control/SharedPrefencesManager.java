package com.parousia.eatouts.control;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SharedPrefencesManager {
	
	private SharedPreferences sharedSettings;
	private Editor editor;
	
	private static String EATOUTS_PREFS = "com.parousia.eatouts.SHARED_PREFERENCES";
	
	
	public SharedPrefencesManager(Context context) {
		
		sharedSettings = context.getSharedPreferences(EATOUTS_PREFS, Context.MODE_PRIVATE);
		editor = sharedSettings.edit();
	}


	public SharedPreferences getSharedSettings() {
		return sharedSettings;
	}
	
	
	
	
}
