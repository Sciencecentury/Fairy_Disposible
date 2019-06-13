package model;

import java.util.List;

public class RegistCheck {

	public boolean Check(List<User> users,User user) {

		if(users.isEmpty()){
			return false;
		}else{
			for(User storage : users){

				if(user.getUserName().equals(storage.getUserName())){

					if(user.getUserPass().equals(storage.getUserPass())){
						return true;
					}
				}
			}
		}
		return false;
	}

}
