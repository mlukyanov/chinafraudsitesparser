package artifactidparser;

import java.util.HashMap;

public class ShopChecker implements States {

  public String htmlText = "";

  public HashMap<String, Integer> checkWords(String text, HashMap<String, Integer> results) {
    try {
      text = text.toLowerCase();
    } catch (NullPointerException n) {
      return results;
    }

    int shopValue = (int) States.LINK_STATE.get(States.NULL);

    for (int i = 0; i < States.SHOP_KEYWORDS.length; i++) {
      if (text.contains(States.SHOP_KEYWORDS[i])) {
        shopValue += (int) States.LINK_STATE.get(States.SHOP_KEYWORDS[i]);
        results.put(States.SHOP_KEYWORDS[i], shopValue);
      }
    }
    /*
     * if (shopValue == States.LINK_STATE.get(States.SHOP)) { return true; }
     */
    return results;
  }

}
