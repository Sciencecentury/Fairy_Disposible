package model;

import java.util.List;

public class RegistCheck {

	private Boolean result;

	public boolean Check(List<User> users,User user) {

		if(users.isEmpty()){
			return result = false;
		}else{
			for(User storage : users){

				if(user.getUserName().equals(storage.getUserName())){

					if(user.getUserPass().equals(storage.getUserPass())){

						return result = true;
					}
				}
			}
		}
		return result = false;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

}
