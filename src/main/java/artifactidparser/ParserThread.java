package artifactidparser;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ParserThread extends Thread implements States {

  public LinkGrabber lgr;
  public ShopChecker shc = new ShopChecker();;
  public HashMap<String, Integer> checkResults = new HashMap<String, Integer>();
  public HashMap<String, Integer> hrefs = new HashMap<String, Integer>();

  public ParserThread(HashMap<String, Integer> h) {
    hrefs = h;
    Entry<String, Integer> entry = hrefs.entrySet().iterator().next();
    lgr = new LinkGrabber(entry.getKey());
  }

  public void run() {
    System.out.println(hrefs);
    Iterator it = lgr.hrefsList.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      try {
        lgr.htmlGrabber(pair.getKey().toString());
      } catch (FileNotFoundException fn) {
        fn.printStackTrace();
      }
      String nextPageHtml = lgr.htmlGrabberStart();
      checkResults = shc.checkWords(nextPageHtml, checkResults);
      System.out.print(States.USER_INFO.get("DOT") + " " + checkResults);

    }

  }

}
