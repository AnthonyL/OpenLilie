package org.data

import org.springframework.dao.DataIntegrityViolationException

class ModuleENTController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def moduleENTService;

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [moduleENTInstanceList: ModuleENT.list(params), moduleENTInstanceTotal: ModuleENT.count()]
    }

    def create() {
        [moduleENTInstance: new ModuleENT(params)]
    }

    def save() {
		// Récupération des paramètres de la requête
		String title = params.title;
		Long createurId = params.createur.id ? null : Long.parseLong(params.createur.id);
		Long projectOwnerId = params.projectOwner.id ? null : Long.parseLong(params.projectOwner.id);
		// Appel au service de création
		ModuleENT moduleInstance = moduleENTService.create(title, createurId, projectOwnerId);
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
		String title = params.title;
		Long createurId = params.createur.id ? null : Long.parseLong(params.createur.id);
		Long projectOwnerId = params.projectOwner.id ? null : Long.parseLong(params.projectOwner.id);
		
		if(moduleENTService.update(title, createurId,  projectOwnerId, version, id).hasErrors()){
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
}
