package com.parousia.eatouts.data;

import java.util.ArrayList;

public class GooglePlacesAPIResponse extends BaseResponse{

	GGeometry geometry;
	String icon;
	String id;
	String name;
	GOpeningHours opening_hours;
	ArrayList<GPhoto> photos;
	int price_level;
	double rating;
	String reference;
	ArrayList<String> types;
	String vicinity;

	public GooglePlacesAPIResponse(GGeometry geometry, String icon, String id,
			String name, GOpeningHours opening_hours, ArrayList<GPhoto> photos,
			int price_level, double rating, String reference,
			ArrayList<String> types, String vicinity) {
		super();
		this.geometry = geometry;
		this.icon = icon;
		this.id = id;
		this.name = name;
		this.opening_hours = opening_hours;
		this.photos = photos;
		this.price_level = price_level;
		this.rating = rating;
		this.reference = reference;
		this.types = types;
		this.vicinity = vicinity;
	}

	public GooglePlacesAPIResponse() {
		super();
	}

	public GGeometry getGeometry() {
		return geometry;
	}

	public void setGeometry(GGeometry geometry) {
		this.geometry = geometry;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GOpeningHours getOpening_hours() {
		return opening_hours;
	}

	public void setOpening_hours(GOpeningHours opening_hours) {
		this.opening_hours = opening_hours;
	}

	public ArrayList<GPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(ArrayList<GPhoto> photos) {
		this.photos = photos;
	}

	public int getPrice_level() {
		return price_level;
	}

	public void setPrice_level(int price_level) {
		this.price_level = price_level;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public ArrayList<String> getTypes() {
		return types;
	}

	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}

	public String getVicinity() {
		return vicinity;
	}

	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}

	private class GPhoto {
		long height;
		String photo_reference;
		long width;

		public GPhoto(long height,String photo_reference, long width) {
			super();
			this.height = height;
			this.photo_reference = photo_reference;
			this.width = width;
		}

		public long getHeight() {
			return height;
		}

		public void setHeight(long height) {
			this.height = height;
		}

		public String getPhoto_reference() {
			return photo_reference;
		}

		public void setPhoto_reference(String photo_reference) {
			this.photo_reference = photo_reference;
		}

		public long getWidth() {
			return width;
		}

		public void setWidth(long width) {
			this.width = width;
		}

	}

	private class GOpeningHours {
		boolean open_now;

		public GOpeningHours(boolean open_now) {
			super();
			this.open_now = open_now;
		}

		public boolean isOpen_now() {
			return open_now;
		}

		public void setOpen_now(boolean open_now) {
			this.open_now = open_now;
		}

	}

	private class GGeometry {
		GLocation location;

		public GGeometry(GLocation location) {
			super();
			this.location = location;
		}

		public GLocation getLocation() {
			return location;
		}

		public void setLocation(GLocation location) {
			this.location = location;
		}

		private class GLocation {
			double lat;
			double lng;

			public GLocation(double lat, double lng) {
				super();
				this.lat = lat;
				this.lng = lng;
			}

			public double getLat() {
				return lat;
			}

			public void setLat(double lat) {
				this.lat = lat;
			}

			public double getLng() {
				return lng;
			}

			public void setLng(double lng) {
				this.lng = lng;
			}

		}

	}
}
