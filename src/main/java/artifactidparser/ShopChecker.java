package artifactidparser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShopChecker implements StatesEng {
	
	private HashMap<String, Integer> shopKeys = new HashMap<String, Integer>();
	public String htmlText = "";
	
	public ShopChecker(String text) {
		htmlText = text.toLowerCase();
		fillKeys();
	}
	
	public String checkWords(String text) {
		String whatWeHave = "";
		fillKeys();
		Iterator it = shopKeys.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
      if(htmlText.contains(pair.getKey().toString())){
      	whatWeHave += pair.getKey() + " | ";
      }
    }
    return whatWeHave;
	}
	
	private void fillKeys() {
		shopKeys.put(StatesEng.SHIPMENT, StatesEng.SHIPMENT_FOUND);
		shopKeys.put(StatesEng.BUY, StatesEng.BUY_FOUND);
		shopKeys.put(StatesEng.ORDER, StatesEng.ORDER_FOUND);
		shopKeys.put(StatesEng.PAYMENT, StatesEng.PAYMENT_FOUND);
	}

}
