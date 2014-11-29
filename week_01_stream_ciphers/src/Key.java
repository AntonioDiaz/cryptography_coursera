import java.util.ArrayList;
import java.util.List;

public class Key {

	public Key(Integer size) {
		super();
		this.setSize(size);
		charsKey = new ArrayList<Integer>();
		for (int i=0; i<size; i++){
			charsKey.add(null);
		}
	}
	
	/** Size of the key */
	private Integer size;
	
	/** List of the characters that compounds the key. */
	private List<Integer> charsKey;

	/**
	 * 
	 * @param position
	 * @param myChar
	 */
	public void addCharToKey(Integer position, Integer myChar) {
		charsKey.set(position, myChar);
	}
	
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public List<Integer> getCharsKey() {
		return charsKey;
	}

	public void setCharsKey(List<Integer> charsKey) {
		this.charsKey = charsKey;
	}
	
	
	
	
	
	
}
