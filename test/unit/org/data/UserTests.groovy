package org.data



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {
	
	def void testCreate() {
		def myUser = new User(username:"user", password:"pass", fullName:"userName", enabled:true)
		assertTrue(myUser.username.equals("user"))
		assertTrue(myUser.password.equals("pass"))
		assertTrue(myUser.fullName.equals("userName"))
		assertTrue(myUser.enabled.equals(true))
		assertTrue(myUser.accountExpired.equals(false))
		assertTrue(myUser.accountLocked.equals(false))
		assertTrue(myUser.passwordExpired.equals(false))
	}

}
