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
		
		// test les limites intérieures de la contraintes de taille
		instance = moduleENTService.create("u");
		assert (instance.hasErrors() == true);
		instance = moduleENTService.create("un");
		assert (instance.hasErrors() == false);
		
		// test les limites supérieurs de la contraintes de taille
		instance = moduleENTService.create("un titre de 30 caractèreioioi");
		assert (instance.hasErrors() == false);
		instance = moduleENTService.create("un titre de 31 caractèresioioi");
		assert (instance.hasErrors() == false);
    }
}
