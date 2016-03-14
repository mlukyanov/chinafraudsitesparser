package artifactidparser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.select.Elements;

public class main {
	public static void main(String[] args) {
		System.out.println("Hello Chine Fraud");
		System.out.println("#############################################################################");
		String chinaLink="http://www.chineseclothingonline.com/";
		
		ParserPoint pp = new ParserPoint(chinaLink);
		pp.start();
		
		
		Iterator it = pp.lgr.hrefsList.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
      System.out.println(pair.getKey() + " = " +  pair.getValue());
    }
 
		System.out.println("#############################################################################");
	}

}
