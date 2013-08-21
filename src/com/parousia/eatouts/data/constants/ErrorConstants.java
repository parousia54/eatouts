package com.parousia.eatouts.data.constants;

import com.parousia.eatouts.data.Error;

public class ErrorConstants {

	public static Error NO_ERROR = new Error(0, "No Error");
	public static Error JSON_FETCH_ERROR = new Error(-1, "Could not fetch data");
	public static Error NO_NETWORK_ERROR = new Error(101,
			"You are not connected to the network. Please verify your network settings");
	public static Error INVALID_SOURCE = new Error(102, "You have not selected this source to query");

}
