package org.data

import static org.junit.Assert.*
import org.junit.*

class ModuleENTControllerIntegrationTests {

	def ModuleENTService
	void testShow(){
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
	
	void testList(){
		def dc = new ModuleENTController();
		
		// without loogedin user
		def model = dc.list(20);
		assert model.connectFullName == "";
		
		
		//with looged in user
		def loggedInUser = User.get(1);
		dc.springSecurityService = [
			encodePassword: 'password',
			reauthenticate: { String u -> true},
			loggedIn: true,
			principal: loggedInUser]
		
		model = dc.list(150);
		assert model.connectFullName != "";
	}
	
	void testSave(){
		def dc = new ModuleENTController();
		//with looged in user
		def loggedInUser = User.get(1);
		dc.springSecurityService = [
			encodePassword: 'password',
			reauthenticate: { String u -> true},
			loggedIn: true,
			principal: loggedInUser]
		dc.params.title="un titre";
		
		dc.save();
		assert dc.response.redirectedUrl.contains("/moduleENT/show")
		
		def  n = ModuleENT.list().size()
		dc.params.title="un titre beaucoup mais beaucoup trop trop long !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
		dc.save();
		assert ModuleENT.list().size() == n
		
	}
	
	void testDeleteNotExist(){
		def dc = new ModuleENTController();
		
		def num = ModuleENT.list().size();
		// no exists
		Long id = Long.parseLong("45487");
		dc.delete(id);
		assert num == ModuleENT.list().size();
		
	}
	
	 void testDeleteExist(){
		 def dc = new ModuleENTController();
		 
		 def num = ModuleENT.list().size();
		 //exists
		 dc.delete(1);
		 assert num -1 == ModuleENT.list().size();
	 }
	 void testUpdate(){
		 
	 }
	
	void testUpdateNotExist(){
		def dc = new ModuleENTController();
		// no exists
		Long id = Long.parseLong("45487");
		dc.update(id, null);
		assert dc.response.redirectedUrl == "/"
	}
	
	void testUpdateExist(){
		def dc = new ModuleENTController();
		dc.params.title ="new title"
		dc.update(2, null);
		assert ModuleENT.get(2).title == "new title";
		assert dc.response.redirectUrl.contains("show");
	
	}
	
	/**
	 * Test que l'update fait bien respecter les contraites sur la classe du domaine
	 */
	void testUpdateExistWihoutModificationCauseFail(){
		def dc = new ModuleENTController();
		dc.params.title ="n"
		dc.update(2, null);
		assert dc.response.redirectedUrl == null
	
	}
	
	
}
