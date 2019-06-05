package model;
import java.io.Serializable;
import java.util.HashMap;
/**
 * @author taiga
 */

public class Account implements Serializable {

	/**
	 *  hasmapに格納
	 */
	private HashMap<String, PassTest> userData = new HashMap<String, PassTest>();

	public Account () {
		userData.put(null, null);
	}

	public HashMap<String, PassTest> getUserData() {
		return userData;
	}

	public void setUserData(HashMap<String, PassTest> userData) {
		this.userData = userData;
	}



}