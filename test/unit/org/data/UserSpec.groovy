package org.data


import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class UserSpec extends Specification {
	
	String getLongString(Integer length) {
		'a' * length
	}
	
	def setup() {
		//mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests(User, [new User(username:"Spock", password:"pass", fullName:"Leonard Nimoy")])
	}

	void validateConstraints(obj, field, error) {
		def validated = obj.validate()
		if (error && error != 'valid') {
			assert !validated
			assert obj.errors[field]
			assert error == obj.errors[field]
		} else {
			assert !obj.errors[field]
		}
		//println ("erreur attendue : "+error+ ", pour le champ : "+field+ ", avec la valeur : "+obj."$field"+", erreur obtenu : "+obj.errors[field])
	}
	
	@Unroll("test user all constraints #field is #error using #val")
	def "test user all constraints"() {
		when:
		def obj = new User("$field": val)

		then:
		validateConstraints(obj, field, error)

		where:
		error                  | field        | val
		'nullable'             | 'fullName'   | null
		'size'                 | 'fullName'   | getLongString(31)
	}
	
	@Unroll("user #field is #error using #val")
	def "test user username constraints"() {
		when:
		def obj = new User("$field": val)

		then:
		validateConstraints(obj, field, error)

		where:
		error                  | field         | val
		'valid'                | 'userName'    | 'Enterprise'
	}
	
	@Unroll("user #field is #error using #val")
	def "test user fullName constraints"() {
		when:
		def obj = new User("$field": val)

		then:
		validateConstraints(obj, field, error)

		where:
		error                  | field         | val
		'nullable'             | 'fullName'    | null
		'size'                 | 'fullName'    | getLongString(31)
		'valid'                | 'fullName'    | 'Captain Kirk'
	}
	
	@Unroll("user #field is #error using #val")
	def "test user password constraints"() {
		when:
		def obj = new User("$field": val)

		then:
		validateConstraints(obj, field, error)

		where:
		error                  | field         | val
		'valid'                | 'password'    | 'pass'
	}
}
