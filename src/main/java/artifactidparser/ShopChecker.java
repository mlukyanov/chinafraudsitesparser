package artifactidparser;

public class ShopChecker implements States {

	public String htmlText = "";

	public boolean checkWords(String text) {
		try {
			text = text.toLowerCase();
		} catch (NullPointerException n) {
			return false;
		}

		int shopValue = (int) States.LINK_STATE.get(States.NULL);

		for (int i = 0; i < States.SHOP_KEYWORDS.length; i++) {
			if (text.contains(States.SHOP_KEYWORDS[i])) {
				shopValue += (int) States.LINK_STATE.get(States.SHOP_KEYWORDS[i]);
			}

		}

		if (shopValue == States.LINK_STATE.get(States.SHOP)) {
			return true;
		}

		return false;
	}

}
