package artifactidparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LinkGrabber {

	private String chinalink;
	private URL url;
	private String htmlGrabbed;
	
	public LinkGrabber(String link) {
		chinalink = link;
	}

	private String grabber() {
		String htmlText = null;
		try {
			url = new URL(chinalink);
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
		    try {
					for (String line; (line = reader.readLine()) != null;) {
						htmlText+=line;
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return htmlText;
	}
	
	public HashMap<Integer, String> hrefExctractor(String text){
		Pattern p = Pattern.compile("href=\"(.*?)\"", Pattern.DOTALL);
		Matcher m = p.matcher(text);
		HashMap<Integer, String> hrefs = new HashMap<Integer, String>();
		int i = 0;
		while (m.find()) {
			hrefs.put(i++, m.group(1));
		}
		return hrefs;
	}
	
	public String joinLinks(Elements els) {
		
		String listString = "";

		for (Element s : els){
			listString += s;
		}
		return listString;
	}
	
	public Elements linkSelector(){
		 Document doc = null;
		 doc = Jsoup.parse(grabber());
		 Elements hrefs = doc.getElementsByAttribute("href");
		 return hrefs;
	}
	
}
