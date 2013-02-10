package org.data



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ModuleENTService)
class ModuleENTServiceTests {

	def moduleENTService = new ModuleENTService();
    void testCreate() {
        ModuleENT instance;
		instance = moduleENTService.create("un titre reconnaissable");
		assert (instance.hasErrors() == false);
    }
}
