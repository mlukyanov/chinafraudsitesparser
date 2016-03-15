package artifactidparser;

import java.util.HashMap;

public interface StatesEng {

// page status verified
	public int UNCHECKED = -2;
	public int CHECKED = -1;
	//public int NULL = 0;
	public int SHIPMENT_FOUND = 2;
	public int BUY_FOUND = 3;
	public int ORDER_FOUND = 4;
	public int PAYMENT_FOUND = 5;

// shop keywords
	public String SHIPMENT = "shipment";
	public String BUY = "buy";
	public String ORDER = "order";
	public String PAYMENT = "paiment";

}
