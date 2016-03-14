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

public class LinkGrabber implements StatesEng{

	private String chinalink;
	private URL url;
	public String htmlGrabbed;
	public HashMap<String, Integer> hrefsList = new HashMap<String, Integer>();;
	
	public LinkGrabber(String link) {
		chinalink = link.trim();
    if (chinalink.endsWith("/")) {
    	chinalink = chinalink.substring(0, chinalink.length() - 1);
    }
	}

	private String grabber() {
		String htmlText = null;
		try {
			url = new URL(chinalink);
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
		}

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
		    try {
					for (String line; (line = reader.readLine()) != null;) {
						htmlText+=line;
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		    
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		htmlGrabbed = htmlText.toLowerCase();
		return htmlText;
	}
	
	public HashMap<String, Integer> hrefExctractor(String text){
		Pattern p = Pattern.compile("href=\"(.*?)\"", Pattern.DOTALL);
		Matcher m = p.matcher(text);
		HashMap<String, Integer> hrefs = new HashMap<String, Integer>();
		int i = 0;
		while (m.find()) {
			hrefs.put(m.group(1), StatesEng.UNCHECKED);
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
	
	public HashMap<String,Integer> linkVerifier(HashMap<String, Integer> hmp){
		String baseUrlLowerCase = chinalink.toLowerCase();
		
		Iterator it = hmp.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
      if(pair.getKey().toString().toLowerCase().indexOf(chinalink) == -1){
      	it.remove();
      }
      
      // prevent recursive loop
      if( Math.abs(chinalink.length() - pair.getKey().toString().length()) == 1 ) {
      	it.remove();
      }
		
  }
    hrefsList.putAll(hmp);
		return hmp;
	} 
	
	public Elements linkSelector(){
		 Document doc = null;
		 doc = Jsoup.parse(grabber());
		 Elements hrefs = doc.getElementsByAttribute("href");
		 return hrefs;
	}
	
	
	
}
