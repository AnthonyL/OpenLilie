package openlilie

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class DomainTestTests extends Specification {

	def setup() {
	}
	String getLongString(Integer length) {
		'a' * length
	}

	void validateConstraints(obj, field, error) {
		
		print "coucou"
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
	
	def cleanup() {
	}

	@Unroll("test domaintest all constraints #field is #error using #val")
	def "test DomainTest all constraints"() {
		when:
		def obj = new DomainTest("$field": val)
	
		then:
		validateConstraints(obj, field, error)
		
		where:
		error                  | field        | val
		'size'                 | 'name'       | getLongString(31)
	}
}