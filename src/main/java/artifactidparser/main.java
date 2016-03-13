package artifactidparser;

import java.util.HashMap;

import org.jsoup.select.Elements;

public class main {
	public static void main(String[] args) {
		System.out.println("Hello Chine Fraud");
		System.out.println("#############################################################################");
		String chinaLink="http://www.chineseclothingonline.com/";
		LinkGrabber lgr = new LinkGrabber(chinaLink);
		Elements els = lgr.linkSelector();
		String links = lgr.joinLinks(els);
		HashMap<Integer, String> hrefs = lgr.hrefExctractor(links);
		System.out.println(hrefs);
		System.out.println("#############################################################################");
	}

}
