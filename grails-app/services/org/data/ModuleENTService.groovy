package org.data

import org.data.ModuleENT;
import org.hibernate.tuple.VersionProperty;
class ModuleENTService {
	
	/**
	 * Crée un nouveau ModuleEnt en base.
	 * @param title2
	 * @return {@link ModuleENT}
	 */
	ModuleENT create(String title2, User currentUser){
		ModuleENT module = new ModuleENT(title2: title2, createur: currentUser);
		module.save(flush:true);
		module;
	}
	
	Boolean isModuleEntExist(Long id){
		return ModuleENT.get(id) != null;
	}
	
	Boolean isAlreadyModified(id, Long version){
		def moduleENTInstance = ModuleENT.get(id)
		return   (version != null && moduleENTInstance.version > version)
           
	}
	
	ModuleENT update(String title2, Long version, Long id){
		ModuleENT instance = ModuleENT.get(id);
		instance.title2 = title2;
		instance.version = version;
		instance.save(flush:true)
		return instance
	}
	
	def delete(Long id){
		ModuleENT instance = ModuleENT.get(id);
		instance.delete();
	}
	
	ModuleENT assignation(Long id, Long projectOwnerId) {
		ModuleENT instance = ModuleENT.get(id);
		instance.projectOwner = User.get(projectOwnerId);
		instance.save(flush:true)
		return instance
	}
}
