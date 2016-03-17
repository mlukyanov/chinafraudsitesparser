package artifactidparser;

import java.util.Iterator;
import java.util.Map;

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
		Boolean shopChecked = shc.checkWords(indexHtml);
		lgr.hrefExctractor(indexHtml);
	}

	public boolean start() {
		System.out.println("Work started. Fill free to wait:");
		boolean shopChecked = false;
		Iterator it = lgr.hrefsList.entrySet().iterator();
		int i = 0;
		while (it.hasNext() && !shopChecked) {
			Map.Entry pair = (Map.Entry) it.next();
			lgr.htmlGrabber(pair.getKey().toString());
			String nextPageHtml = lgr.htmlGrabberStart();
			shopChecked = shc.checkWords(nextPageHtml);
			System.out.print(". ");
			i++;
			if (i % 32 == 0) {
				System.out.println();
			}

			if (shopChecked) {
				System.out.println("Done!");
				return shopChecked;
			}
		}
		System.out.println("Done!");
		return shopChecked;
	}

}
