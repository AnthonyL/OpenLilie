package org.data

import org.security.AuthPerson

class User extends AuthPerson {
	
	String fullName

    static constraints = {
		fullName (unique : true, nullable : false, size:2..30, blank : false)
    }
	
	@Override
	public String toString() {
		return fullName
	}
	
	public boolean isRole(String authority){
		return this.authorities.toList().first().authority.equals(authority)
	}
}
