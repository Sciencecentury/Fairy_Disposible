package model;

public class RegistCheck {
	public boolean Check(Account account,PassTest sha) {

		for(PassTest key : account.getUserData().values()) {
			if(key.equals(sha)) {
				return true;
			}
		}

		return false;

	}

}
