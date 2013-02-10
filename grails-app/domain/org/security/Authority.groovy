package org.security

class Authority {

	String authority
	
	static belongsTo=AuthPerson
	static hasMany=[authPerson: AuthPerson]

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
