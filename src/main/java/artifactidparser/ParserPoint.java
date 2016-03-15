package artifactidparser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.select.Elements;

public class ParserPoint {

	public String currentUrl = "";
	public LinkGrabber lgr;
	public ShopChecker shc;
	public String indexHtml = "";
	
	
	public ParserPoint(String url) {
		currentUrl = url.trim();
		lgr = new LinkGrabber(currentUrl);
		indexHtml = lgr.htmlGrabberStart();
		shc = new ShopChecker();
		String shopChecked = shc.checkWords(indexHtml);
		lgr.hrefExctractor(indexHtml);
	}
	
	public boolean start() {
		Iterator it = lgr.hrefsList.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
  		lgr = new LinkGrabber(pair.getKey().toString());
  		String nextPageHtml = lgr.htmlGrabberStart();
  		String shopChecked = shc.checkWords(nextPageHtml);      
  		if(shc.BUY_DETECTED && shc.ORDER_DETECTED && shc.SHIPMENT_DETECTED) {
  			return true;
  		}
    }
    return false;
	}

}
