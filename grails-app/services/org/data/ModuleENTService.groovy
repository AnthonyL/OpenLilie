package org.data

import org.data.ModuleENT;
import org.hibernate.tuple.VersionProperty;
class ModuleENTService {

    def serviceMethod() {

    }
	
	/**
	 * Crée un nouveau ModuleEnt en base.
	 * @param title
	 * @return {@link ModuleENT}
	 */
	ModuleENT create(String title, Long createurId = null, Long projectOwnerId = null){
		User createur = User.get(createurId)
		User projectOwner = User.get(projectOwnerId);
		ModuleENT module = new ModuleENT(title: title, createur: createur, projectOwner: projectOwner);
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
	
	ModuleENT update(String title, Long createurId, Long projectOwnerId, Long version, Long id){
		ModuleENT instance = ModuleENT.get(id);
		instance.title = title;
		instance.version = version;
		instance.createur = User.get(createurId);
		instance.projectOwner = User.get(projectOwnerId);
		instance.save(flush:true)
		return instance
	}
	
	def delete(Long id){
		ModuleENT instance = ModuleENT.get(id);
		instance.delete(flush:true);
	}
}
