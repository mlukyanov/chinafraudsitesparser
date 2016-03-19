package artifactidparser;

import java.util.HashMap;

public interface States {

  // page status verified
  public static HashMap<String, Integer> LINK_STATE = new HashMap<String, Integer>() {
    {
      put("uncheked", -1);
      put("null", 0);
      put("shipment", 1);
      put("cart", 2);
      put("order", 3);
    }
  };

  // shop keywords
  public static String[] SHOP_KEYWORDS = { "shipment", "cart", "order" };

  // page status verified
  public static HashMap<String, String> USER_INFO = new HashMap<String, String>() {
    {
      put("RGL", "Removing garbage links");
      put("ED", "Exctracting Domain: ");
      put("WSFFTW", "Work started. Fill free to wait:");
      put("SD", "Shop Detected");
      put("IINAS", "It is not a shop");
      put("DOT", ". ");
    }
  };

  // other
  public static String NULL = "null";
  public static int DELIMITER = 32;
}
