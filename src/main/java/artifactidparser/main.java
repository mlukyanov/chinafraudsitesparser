package artifactidparser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.select.Elements;

public class main {
	public static void main(String[] args) {
		System.out.println("Hello Link! Lets check you shop or no)");
		String chinaLink="http://www.chineseclothingonline.com/";
		
		ParserPoint pp = new ParserPoint(chinaLink);
		boolean shopResult = pp.start();
		
		if(shopResult) {
			System.out.println("Shop Detected");
		}else {
			System.out.println("It is not a shop");
		}
		System.out.println("#############################################################################");
	}

}
