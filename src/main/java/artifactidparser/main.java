package artifactidparser;

public class main {
  public static void main(String[] args) {
    System.out.println("Hi! Let's check link for shopping some stuffs.");
    String linkToCheck = "http://www.chineseclothingonline.com/";
    // String linkToCheck = "https://en.wikipedia.org/wiki/Main_Page/";
    // String linkToCheck = "http://www.aliexpress.com";
    // String linkToCheck = "http://en.savefrom.net/";
    // String linkToCheck = "http://www.dailymail.co.uk/home/index.html";
    // String linkToCheck = "https://www.bitcoin.com/";
    // String linkToCheck = "http://www.amazon.co.uk/";
    // String linkToCheck = "http://www.ringcentral.com/";
    // String linkToCheck = "https://www.facebook.com";
    // String linkToCheck = "http://www.fedex.com/us/";

    if (args.length > 0) {
      linkToCheck = args[0].toString().trim().toLowerCase();
    }
    float src = (float) 4.9;
    int res = (int) src;
    System.out.println("res: " + res);

    ParserPoint pp = new ParserPoint(linkToCheck);
    boolean shopResult = false;
    shopResult = pp.deploy();

    /*
     * System.out.println(); if (shopResult) {
     * 
     * System.out.println("Shop Detected"); } else { System.out.println(
     * "It is not a shop"); } System.out.println(
     * "#############################################################################"
     * );
     */
  }

}
