package org.security

import org.apache.commons.lang.builder.HashCodeBuilder

class AuthPersonAuthority implements Serializable {

	AuthPerson authPerson
	Authority authority

	boolean equals(other) {
		if (!(other instanceof AuthPersonAuthority)) {
			return false
		}

		other.authPerson?.id == authPerson?.id &&
			other.authority?.id == authority?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (authPerson) builder.append(authPerson.id)
		if (authority) builder.append(authority.id)
		builder.toHashCode()
	}

	static AuthPersonAuthority get(long authPersonId, long authorityId) {
		find 'from AuthPersonAuthority where authPerson.id=:authPersonId and authority.id=:authorityId',
			[authPersonId: authPersonId, authorityId: authorityId]
	}

	static AuthPersonAuthority create(AuthPerson authPerson, Authority authority, boolean flush = false) {
		new AuthPersonAuthority(authPerson: authPerson, authority: authority).save(flush: flush, insert: true)
	}

	static boolean remove(AuthPerson authPerson, Authority authority, boolean flush = false) {
		AuthPersonAuthority instance = AuthPersonAuthority.findByAuthPersonAndAuthority(authPerson, authority)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(AuthPerson authPerson) {
		executeUpdate 'DELETE FROM AuthPersonAuthority WHERE authPerson=:authPerson', [authPerson: authPerson]
	}

	static void removeAll(Authority authority) {
		executeUpdate 'DELETE FROM AuthPersonAuthority WHERE authority=:authority', [authority: authority]
	}

	static mapping = {
		id composite: ['authority', 'authPerson']
		version false
	}
}
