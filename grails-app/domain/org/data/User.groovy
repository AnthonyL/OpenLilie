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
}
