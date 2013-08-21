package com.parousia.eatouts.logic;

import com.parousia.eatouts.control.SharedPrefencesManager;

import android.app.Application;

public class EatOutsApplication extends Application{

	private static EatOutsApplication instance;
	private SharedPrefencesManager eatOutsSettings;
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		initializeLocationUtility();
		initializePreferencesUtility();
		initializeNetworkUtility();
		
	}
	

	public static EatOutsApplication getInstance() {
		if(instance == null){
			return new EatOutsApplication();
		}
		return instance;
	}
	

	private void initializeLocationUtility() {
		
	}

	private void initializePreferencesUtility() {
		setEatOutsSettings(new SharedPrefencesManager(this));
	}
	

	private void initializeNetworkUtility() {
		
	}


	public SharedPrefencesManager getEatOutsSettings() {
		return eatOutsSettings;
	}


	public void setEatOutsSettings(SharedPrefencesManager eatOutsSettings) {
		this.eatOutsSettings = eatOutsSettings;
	}


	
}
