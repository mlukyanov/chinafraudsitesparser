package artifactidparser;

import java.util.HashMap;

public interface States {

	// page status verified
	public static HashMap<String, Integer> LINK_STATE = new HashMap<String, Integer>() {
		{
			put("uncheked", -1);
			put("null", 0);
			put("shipment", 1);
			put("buy", 2);
			put("order", 3);
			put("shop", 6);
		}
	};

	// shop keywords
	public static String NULL = "null";
	public static String SHIPMENT = "shipment";
	public static String BUY = "buy";
	public static String ORDER = "order";
	public static String SHOP = "shop";


	public static String[] SHOP_KEYWORDS = {"shipment", "buy", "order"};
}
