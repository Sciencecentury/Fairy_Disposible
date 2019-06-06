package model;

import java.util.HashMap;

public class RegistCheck {


	public boolean Check(HashMap<String,PassTest> user ,Users users) {
		if(users.getUser().containsKey(user.keySet())){
			if(users.getUser().containsValue(user.get(user.keySet()))){
				return true;
			}
		}
		return false;
	}

}
