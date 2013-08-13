package com.parousia.eatouts.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefUtil {
	
	private static String EATOUTS_PREFS = "com.parousia.eatouts";
	
	public static SharedPreferences getPrefs(Context context){
		return context.getSharedPreferences(EATOUTS_PREFS, Context.MODE_PRIVATE);
	}
}
