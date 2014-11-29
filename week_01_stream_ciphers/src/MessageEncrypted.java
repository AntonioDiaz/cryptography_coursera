import java.util.ArrayList;
import java.util.List;

/**
 * Representation of the message encrypted.
 * 
 * @author toni
 *
 */

public class MessageEncrypted {

	private String cipherText;
	public static final String ASTERISK= "*";

	public MessageEncrypted(String str) {
		this.cipherText = str;
	}
	
	public String getCipherText() {
		return cipherText;
	}

	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}
	
	public List<Integer> generateList(){
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < this.cipherText.length(); i=i+2) {
			String subStrEncription = this.cipherText.substring(i, i+2);			
			result.add(Integer.parseInt(subStrEncription, 16));
		}
		return result;		
	}
	
	public String deCryptMsg (Key key){		
		String result = "";
		List<Integer> generateList = this.generateList();
		List<Integer> charsKey = key.getCharsKey();
		for (int i = 0; i < generateList.size(); i++) {
			if (i<charsKey.size()) {
				Integer integer = charsKey.get(i);
				if (integer==null) {
					result += ASTERISK;
				} else {
					int myXored = integer ^ generateList.get(i); 
					result += Character.toString((char) myXored);
				}
			} else {
				result += ASTERISK;
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		String result = "";
		List<Integer> generateList = this.generateList();
		for (Integer integer : generateList) {
			if (integer!=null) {
				String str = Integer.toString(integer, 16) + ",";
				if (str.length()<3) {
					str = "0" + str;
				}
				result += str;
				
			} else {
				result += "__,";
			}
		}
		return result;
	}
	
}
