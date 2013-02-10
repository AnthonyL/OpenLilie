package org.data

import org.data.ModuleENT;
import org.hibernate.tuple.VersionProperty;
class ModuleENTService {
	
	def springSecurityService
	
	/**
	 * Crée un nouveau ModuleEnt en base.
	 * @param title
	 * @return {@link ModuleENT}
	 */
	ModuleENT create(String title){
		User createur = User.get(springSecurityService.principal.id)
		ModuleENT module = new ModuleENT(title: title, createur: createur);
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
	
	ModuleENT update(String title, Long version, Long id){
		ModuleENT instance = ModuleENT.get(id);
		instance.title = title;
		instance.version = version;
		instance.save(flush:true)
		return instance
	}
	
	def delete(Long id){
		ModuleENT instance = ModuleENT.get(id);
		instance.delete(flush:true);
	}
	
	ModuleENT assignation(Long id, Long projectOwnerId) {
		ModuleENT instance = ModuleENT.get(id);
		instance.projectOwner = User.get(projectOwnerId);
		instance.save(flush:true)
		return instance
	}
}
