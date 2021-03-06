package org.data

import org.springframework.dao.DataIntegrityViolationException
import org.data.User
import org.data.ModuleENT

class ModuleENTController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def moduleENTService;
	def springSecurityService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
		def moduleList
		def fullName
		
		if(springSecurityService.loggedIn){
			User user = User.get(springSecurityService.principal.id)
			fullName = user.fullName
			if(!user.isRole("ROLE_GOVERNOR")){
				moduleList = ModuleENT.createCriteria().list{
					eq("projectOwner", user)
				}
				println moduleList
			}else{
				moduleList = ModuleENT.list()
			}
		} else {
			fullName = ""
			moduleList = ModuleENT.list()
		}
	
        params.max = Math.min(max ?: 10, 100)
        [connectFullName: fullName, moduleENTInstanceList: moduleList, moduleENTInstanceTotal: moduleList.size()]
    }

    def create() {
        [moduleENTInstance: new ModuleENT(params)]
    }

    def save() {
		// Récupération des paramètres de la requête
		String title2 = params.title2;
		User createur = User.get(springSecurityService.principal.id)
		
		// Appel au service de création
		ModuleENT moduleInstance = moduleENTService.create(title2, createur);
		// S'il y a des erreur sur le module, c'est qu'il n'a pas été sauvegardé
        if (moduleInstance.hasErrors()) {
            render(view: "create", model: [moduleENTInstance: moduleInstance])
            return
        }
		
        flash.message = message(code: 'default.created.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), moduleInstance.id])
        redirect(action: "show", id: moduleInstance.id)
    }

    def show(Long id) {
        if (!moduleENTService.isModuleEntExist(id)) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), id])
            redirect(action: "list")
            return
        }
		else{
			def moduleENTInstance = ModuleENT.get(id)
			[moduleENTInstance: moduleENTInstance]
		}

    }

    def edit(Long id) {
        if (!moduleENTService.isModuleEntExist(id)) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), id])
            redirect(action: "list")
            return
        }
		else{
			def moduleENTInstance = ModuleENT.get(id)
			[moduleENTInstance: moduleENTInstance]
		}

    }

    def update(Long id, Long version) {
		
        if (!moduleENTService.isModuleEntExist(id)) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), id])
            redirect(action: "list")
            return
        }
		
		if(moduleENTService.isAlreadyModified(id, version))
        {
				def moduleENTInstance = ModuleENT.get(id)
                moduleENTInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'moduleENT.label', default: 'ModuleENT')] as Object[],
                          "Another user has updated this ModuleENT while you were editing")
                render(view: "edit", model: [moduleENTInstance: moduleENTInstance])
                return
            
        }
		
		// Récupération des paramètres de la requête
		String title2 = params.title2;
		
		if(moduleENTService.update(title2, version, id).hasErrors()){
			def moduleENTInstance = ModuleENT.get(id)
			render(view: "edit", model: [moduleENTInstance: moduleENTInstance])
			return
		}

        flash.message = message(code: 'default.updated.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), id])
        redirect(action: "show", id: id)
    }

    def delete(Long id) {
        
        if (!moduleENTService.isModuleEntExist(id)) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), id])
            redirect(action: "list")
            return
        }

        try {
            moduleENTService.delete(id)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def assignation(Long id){
		def listProjectOwner = User.createCriteria().list {
			authority{eq("authority", "ROLE_PROJECTOWNER")}
		}
		[moduleENTInstance: ModuleENT.get(id), listProjectOwner: listProjectOwner]
	}
	
	def affectation(Long moduleENTId){
		moduleENTService.assignation(moduleENTId, Long.valueOf(params.projectOwner.id))
		redirect(action:"show", id: moduleENTId)
	}
}
