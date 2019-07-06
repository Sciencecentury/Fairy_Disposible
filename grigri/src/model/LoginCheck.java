package model;

import java.util.List;

public class LoginCheck {
	public boolean Check(List<User> UsersStorage,List<User> LoginUser) {

		Judg cnt = new Judg();
		cnt.cnt();

		for(User storage : UsersStorage){
			cnt.setCnt(cnt.getCnt()+1);
			for(User LoginStorage : LoginUser){

				if(LoginStorage.getUserName().equals(storage.getUserName())){

					if(LoginStorage.getUserPass().equals(storage.getUserPass())){
						return true;
					}
				}
			}
		}
		return false;
	}
}
