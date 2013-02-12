package org.data

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import org.security.Authority
import org.security.AuthPersonAuthority


/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(ModuleENT)
class ModuleENTSpec extends Specification {

    String getLongString(Integer length) {
		'a' * length
	}
	
	def setup() {
		//mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
		def userCreator = new User(username:"creator", password:"pass", fullName:"creatorName", enabled:true)
		def userProjectOwner = new User(username:"projectOwner", password:"pass", fullName:"projectOwnerName", enabled:true)
		mockForConstraintsTests(User, [new ModuleENT(title: "Star Trek", createur: userCreator, projectOwner: userProjectOwner)])
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
	
	@Unroll("test moduleENT all constraints #field is #error using #val")
	def "test moduleENT all constraints"() {
		when:
		def obj = new ModuleENT("$field": val)

		then:
		validateConstraints(obj, field, error)

		where:
		error                  | field          | val
		'valid'                | 'title'        | 'Star Trek 2'
		'valid'                | 'title'        | 'Vers l\'infini et l\'au delà'
	}
}
