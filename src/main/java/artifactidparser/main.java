package artifactidparser;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.select.Elements;

public class main {
	public static void main(String[] args) {
		System.out.println("Hello Link! Lets check you shop or no)");
		String linkToCheck = "http://www.chineseclothingonline.com/";
		// String linkToCheck = "https://en.wikipedia.org/wiki/Main_Page/";
		// String linkToCheck = "http://www.aliexpress.com";

		ParserPoint pp = new ParserPoint(linkToCheck);
		boolean shopResult = false;
		try {
			shopResult = pp.start();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (shopResult) {
			System.out.println("Shop Detected");
		} else {
			System.out.println("It is not a shop");
		}
		System.out.println("#############################################################################");
	}

}
