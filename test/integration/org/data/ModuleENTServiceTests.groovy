package org.data



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ModuleENTService)
class ModuleENTServiceTests extends GroovyTestCase {
	/**
	 * Tout est dans la même méthode car l'utilisation de plusieurs méthodes de tests porovoquent des erreurs assez étranges 
	 * (même dans le cas où les tests sont identiques dans les deux méthodes)
	 */
	@Test
    void testAll() {
		
		// create
    	def moduleENTService = new ModuleENTService();
        ModuleENT instance;
		instance = moduleENTService.create("un titre reconnaissable");
		assert (instance.hasErrors() == false);
		
		instance = moduleENTService.create("u");
		assert (instance.hasErrors() == true);
		assert (ModuleENT.get(instance.id) == null);
		instance = moduleENTService.create("un");
		assert (instance.hasErrors() == false);
		assert (ModuleENT.get(instance.id) != null);
		
		instance = moduleENTService.create("un titre de 30 caractèreioioie");
		assert (instance.hasErrors() == false);
		assert (ModuleENT.get(instance.id) != null);
		instance = moduleENTService.create("un titre de 31 caractèresioioie");
		assert (instance.hasErrors() == true);
		assert (ModuleENT.get(instance.id) == null);
		
		// update
		instance = moduleENTService.create("un titre reconnaissable1", null, null);
		instance = moduleENTService.update("un titre reconnaissable2", null, null, instance.getVersion(), instance.getId());
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
