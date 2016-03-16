package artifactidparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LinkGrabber implements States {

	private String someLink;
	private URL url;
	public String htmlGrabbed;
	public String domain;
	public HashMap<String, Integer> hrefsList = new HashMap<String, Integer>();;

	public LinkGrabber(String link) {
		someLink = link.trim();
		if (someLink.endsWith("/")) {
			someLink = someLink.substring(0, someLink.length() - 1);
		}
		extractDomain();
	}

	public void extractDomain() {
		try {
			url = new URL(someLink);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		domain = url.getHost();
		if (domain.startsWith("www.")) {
			domain = domain.substring(4);
		}
		// System.out.println("Donain is: " + domain);

	}

	public String htmlGrabber(String link) {
		String htmlText = null;
		try {
			url = new URL(link);
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
		}

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
			try {
				for (String line; (line = reader.readLine()) != null;) {
					htmlText += line;

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return htmlText;
	}

	public String htmlGrabberStart() {
		return htmlGrabber(someLink);
	}

	public HashMap<String, Integer> hrefExctractor(String text) {
		Pattern p = Pattern.compile("href=\"(.*?)\"", Pattern.DOTALL);
		Matcher m = p.matcher(text);
		HashMap<String, Integer> hrefs = new HashMap<String, Integer>();
		while (m.find()) {
			hrefs.put(m.group(1), States.UNCHECKED);
			// System.out.println(m.group(1));
		}
		hrefsList.putAll(linkVerifier(hrefs));

		return hrefs;
	}

	private HashMap<String, Integer> linkVerifier(HashMap<String, Integer> hmp) {
		String baseUrlLowerCase = someLink.toLowerCase();

		Iterator it = hmp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getKey().toString().toLowerCase().indexOf(domain) == -1) {
				it.remove();
			}
			/*
			 * try { // prevent recursive loop if( Math.abs(someLink.length() -
			 * pair.getKey().toString().length()) == 1 ) { it.remove(); }
			 * }catch(IllegalStateException i) { i.printStackTrace(); }
			 */
		}
		return hmp;
	}

}
