package model;

import java.util.List;

public class RegistCheck {

	public boolean Check(List<User> users_storage,User user) {

		for(User storage : users_storage){

			if(user.getUserName().equals(storage.getUserName())){

				if(user.getUserPass().equals(storage.getUserPass())){
					return true;
				}
			}
		}
		return false;
	}
}
