package artifactidparser;

import java.util.HashMap;

import org.jsoup.select.Elements;

public class ParserPoint {

	public String currentUrl = "";
	public LinkGrabber lgr;
	
	public ParserPoint(String url) {
		currentUrl = url.trim();
		lgr = new LinkGrabber(currentUrl);
	}
	
	public void start() {
		Elements els = lgr.linkSelector();
		String links = lgr.joinLinks(els);
		HashMap<String, Integer> hrefs = lgr.hrefExctractor(links);
    hrefs = lgr.linkVerifier(hrefs);
	}

}
