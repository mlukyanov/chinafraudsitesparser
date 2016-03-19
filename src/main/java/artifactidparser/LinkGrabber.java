package artifactidparser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

public class LinkGrabber implements States {

  private String someLink;
  private URL url;
  public String htmlGrabbed;
  public String domain;
  public HashMap<String, Integer> hrefsList = new HashMap<String, Integer>();

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
    System.out.println(States.USER_INFO.get("ED") + domain);
  }

  public String htmlGrabber(String link) throws FileNotFoundException {
    String htmlText = null;
    try {
      url = new URL(link);
    } catch (MalformedURLException m) {
      return htmlText;
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
      try {
        for (String line; (line = reader.readLine()) != null;) {
          htmlText += line;

        }
      } catch (IOException e) {
        return htmlText;
      }

    } catch (UnsupportedEncodingException ue) {
      ue.printStackTrace();
    } catch (FileNotFoundException fn) {
      fn.printStackTrace();
    } catch (IOException io) {
      io.printStackTrace();
    }
    return htmlText;
  }

  public String htmlGrabberStart() {
    try {
      return htmlGrabber(someLink);
    } catch (FileNotFoundException fn) {
      fn.printStackTrace();
    }
    return "";
  }

  public boolean hrefExctractor(String text) {
    Pattern p = Pattern.compile("href=\"(.*?)\"", Pattern.DOTALL);
    Matcher m = p.matcher(text);
    while (m.find()) {
      hrefsList.put(m.group(1), States.LINK_STATE.get("uncheked"));
    }
    if (hrefsList.isEmpty()) {
      return false;
    }
    linkVerifier();
    return true;
  }

  private boolean linkVerifier() {
    System.out.println(States.USER_INFO.get("RGL"));
    String baseUrlLowerCase = someLink.toLowerCase();
    HashMap<String, Integer> hrefs = new HashMap<String, Integer>();
    Iterator it = hrefsList.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      int sitenameIndex = pair.getKey().toString().toLowerCase().indexOf(domain);
      if (sitenameIndex != -1 && sitenameIndex < 20) {
        hrefs.put(pair.getKey().toString(), (int) pair.getValue());
      }
      it.remove();
    }
    if (hrefs.isEmpty()) {
      return false;
    }
    hrefsList = hrefs;
    return true;
  }

}
