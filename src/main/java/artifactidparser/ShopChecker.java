package artifactidparser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShopChecker implements States {

	private HashMap<String, Integer> shopKeys = new HashMap<String, Integer>();
	public String htmlText = "";

	public boolean ORDER_DETECTED = false;
	public boolean SHIPMENT_DETECTED = false;
	public boolean BUY_DETECTED = false;

	public ShopChecker() {
		fillKeys();
	}

	public String checkWords(String text) {
		try {
			text = text.toLowerCase();
		}catch(NullPointerException n){
			return States.NULL + "";
		}
		
		String whatWeHave = "";
		fillKeys();
		Iterator it = shopKeys.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			if (text.contains(States.ORDER)) {
				ORDER_DETECTED = true;
			}

			if (text.contains(States.SHIPMENT)) {
				SHIPMENT_DETECTED = true;
			}

			if (text.contains(States.BUY)) {
				BUY_DETECTED = true;
			}

			
			  if(text.contains(pair.getKey().toString())){ whatWeHave +=
			  pair.getKey() + " | "; }
			 
		}
		return whatWeHave;
	}

	private void fillKeys() {
		shopKeys.put(States.SHIPMENT, States.SHIPMENT_FOUND);
		shopKeys.put(States.BUY, States.BUY_FOUND);
		shopKeys.put(States.ORDER, States.ORDER_FOUND);
	}

}
