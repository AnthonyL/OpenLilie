package org.data

import static org.junit.Assert.*
import org.junit.*

class ModuleENTControllerTests {

	def ModuleENTService
	void testSave(){
		def controller = new ModuleENTController();
		assert null == controller.show(0);
		ModuleENT m = ModuleENT.get(1)
		assert null != controller.show(1);
		assert controller.show(1).moduleENTInstance.id == m.id;
	}
	
	void testEdit(){
		def controller = new ModuleENTController();
		assert null == controller.edit(0);
		ModuleENT m = ModuleENT.get(1);
		def model = controller.edit(1);
		assert model != null;
		assert model.moduleENTInstance.title == m.title;
	}
	
}
