package org.data

import org.security.AuthPerson

class User extends AuthPerson {
	
	String fullName

    static constraints = {
    }
	
	@Override
	public String toString() {
		return fullName
	}
	
	public boolean isRole(String authority){
		return this.authorities.toList().first().authority.equals(authority)
	}
}
