package artifactidparser;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParserPoint implements States {

  public String currentUrl = "";
  public LinkGrabber lgr;
  public ShopChecker shc;
  public String indexHtml = "";
  public int hrefsCounter = 0;
  public HashMap<String, Integer> checkResults = new HashMap<String, Integer>();
  public HashMap<Integer, Thread> threadsMap = new HashMap<Integer, Thread>();
  public int[] threads;

  public ParserPoint() {
    
  }
  
  public ParserPoint(String url) {
    currentUrl = url.trim();
    lgr = new LinkGrabber(currentUrl);
    indexHtml = lgr.htmlGrabberStart();
    shc = new ShopChecker();
    checkResults = shc.checkWords(indexHtml, checkResults);
    lgr.hrefExctractor(indexHtml);
    System.out.println(States.USER_INFO.get("WSFFTW"));
  }

  public boolean deploy() {
    boolean shopChecked = false;
    Iterator it = lgr.hrefsList.entrySet().iterator();
    HashMap<String, Integer> hrefsFoThread = new HashMap<String, Integer>(); 
    
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      hrefsFoThread.put(pair.getKey().toString(), (int) pair.getValue());
      hrefsCounter++;
      if (hrefsCounter % States.DELIMITER == 0) {
        spawnThread(hrefsFoThread);
        hrefsFoThread.clear();
        System.out.println();
      }

    }

/*    
    while (it.hasNext() && !shopChecked) {
      Map.Entry pair = (Map.Entry) it.next();
      try {
        lgr.htmlGrabber(pair.getKey().toString());
      } catch (FileNotFoundException fn) {
        fn.printStackTrace();
      }
      String nextPageHtml = lgr.htmlGrabberStart();
      checkResults = shc.checkWords(nextPageHtml, checkResults);
      System.out.print(States.USER_INFO.get("DOT"));

      counter++;
      if (counter % States.DELIMITER == 0) {
        System.out.println();
      }

      if (checkResults.size() == States.SHOP_KEYWORDS.length) {
        shopChecked = true;
        return shopChecked;
      }
    }
*/
    return shopChecked;
  }

  private void spawnThread(HashMap<String, Integer> hrefsPart){
    ParserThread pth = new ParserThread(hrefsPart);
    threadsMap.put(hrefsCounter, pth);
    pth.run();
    
    /*
    boolean threadsFinished = true;
    int threadsNum = (int) (lgr.hrefsList.size() / States.DELIMITER);
    int threadNext = lgr.hrefsList.size();
   
    int counter = 0;
    while(threadsFinished) {
      if(threadNext <= 0) {
        threadsFinished = false;
        //System.out.println("--");
        break;
      }
      threadsMap.put(counter, new ParserThread(hrefsPart) );
      System.out.println(threadNext);
      counter++;
      threadNext -= States.DELIMITER;
      
    }
   */ 
  } 

}
