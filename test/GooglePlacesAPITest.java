import junit.framework.TestCase;

import org.junit.Test;

import com.google.gson.Gson;
import com.parousia.eatouts.data.GooglePlacesAPIResponse;


public class GooglePlacesAPITest extends TestCase {
	
	@Test
	void testGooglePlacesAPIResponseParse(){
		
		Gson gson = new Gson();
		String toParse = " {\"geometry\":{\"location\":{\"lat\":-33.87054,\"lng\":151.198815}},\"icon\":\"http://maps.gstatic.com/mapfiles/place_api/icons/cafe-71.png\",\"id\":\"c71365287e7606bd21a3311d21fda087830b7813\",\"name\":\"Pancakes on the Rocks\",\"opening_hours\":{\"open_now\":true},\"photos\":[{\"height\":1224,\"html_attributions\":[\"\u003ca href=\"https://plus.google.com/105663944571530352563\"\u003eJoshua Gilmore\u003c/a\u003e\"],\"photo_reference\":\"CnRoAAAAytmWUuQSeBK8NinOv07D3HcilR_CrQgcFLRb0UBkCj0YZ2pDddDvGnMT00VWjkqV_KENSS3fVCtdGKAPiMWvp4XrqwQ6QlBT2zvSocPwu2-RaMabEPt7xET7VGxcGES2qLwFyWXOryUUBe1xxiFrpBIQtoo1havldzcMqTQt_uKVTBoUXviYqUMFdl6GzCgBoA3byZrodxM\",\"width\":1632}],\"price_level\":2,\"rating\":3.8,\"reference\":\"CoQBcgAAAAAULitLKM3614g3UEOvhal6JKADyAzdeHmpDRb0yzYURF2aypUa-PsIVP23DAGU58iEYs2SRRCeisBx7e-ww4nGrX93hgL6-3-Iz6vCZo4a9C5RBFN0crPP3lcdZp3Jf191QEymPydH0MYkX1_XZSKp-IflhSUhWH1gSEz84BeQEhDl8o4-hsWuQSTbp8t6x3Z9GhRm8BUhKu2-SaK2yiqi2u7Rcc5K5Q\",\"types\":[\"cafe\",\"restaurant\",\"food\",\"establishment\"],\"vicinity\":\"Harbourside Shopping Centre,Darling Harbour/227 & 229-230 Darling Drive, Sydney\"}";
		
		GooglePlacesAPIResponse response = gson.fromJson(toParse, GooglePlacesAPIResponse.class);
		
		assertNotNull(response);
		
	}

}
