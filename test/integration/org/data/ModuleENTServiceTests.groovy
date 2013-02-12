package org.data



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ModuleENTService)
class ModuleENTServiceTests extends GroovyTestCase {

    void testAll() {
		def currentUser = User.findByFullName("Anthony Letourneur")
		
		// create
    	def moduleENTService = new ModuleENTService();
        ModuleENT instance;
		instance = moduleENTService.create("un titre reconnaissable", currentUser);
		assert (instance.hasErrors() == false);
		
		instance = moduleENTService.create("u", currentUser);
		assert (instance.hasErrors() == true);
		assert (ModuleENT.get(instance.id) == null);
		instance = moduleENTService.create("un", currentUser);
		assert (instance.hasErrors() == false);
		assert (ModuleENT.get(instance.id) != null);
		
		instance = moduleENTService.create("un titre de 30 caractèreioioie", currentUser);
		assert (instance.hasErrors() == false);
		assert (ModuleENT.get(instance.id) != null);
		instance = moduleENTService.create("un titre de 31 caractèresioioie", currentUser);
		assert (instance.hasErrors() == true);
		assert (ModuleENT.get(instance.id) == null);
		
		// update
		instance = moduleENTService.create("un titre reconnaissable1", currentUser);
		instance = moduleENTService.update("un titre reconnaissable2", instance.getVersion(), instance.getId());
		assert ("un titre reconnaissable2" == instance.getTitle())
		
		// exist
		assert(true == moduleENTService.isModuleEntExist(instance.getId()));
		
		// delete
		moduleENTService.delete(instance.getId());
		assert (null == ModuleENT.get(instance.getId()));
		
		// exist
		assert(false == moduleENTService.isModuleEntExist(instance.getId()));
    }
}
