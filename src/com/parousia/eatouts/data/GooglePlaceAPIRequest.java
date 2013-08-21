package com.parousia.eatouts.data;

public class GooglePlaceAPIRequest extends BaseRequest {

	public GooglePlaceAPIRequest(long currentLat, long currentLong,
			String currentAddress) {
		super(currentLat, currentLong, currentAddress);
	}

	String googlePlacesAPIKey;
	int radius;
	boolean sensor;
	public static final String QUERY_TYPE = "restaurant|cafe|food|meal_delivery|meal_takeaway";
	private static final String PLACES_SEARCH_URL =  "https://maps.googleapis.com/maps/api/place/search/json?";

	public String getGooglePlacesAPIKey() {
		return googlePlacesAPIKey;
	}

	public void setGooglePlacesAPIKey(String googlePlacesAPIKey) {
		this.googlePlacesAPIKey = googlePlacesAPIKey;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean isSensor() {
		return sensor;
	}

	public void setSensor(boolean sensor) {
		this.sensor = sensor;
	}

}
