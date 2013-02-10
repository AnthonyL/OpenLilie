package org.data

import org.springframework.dao.DataIntegrityViolationException

class ModuleENTController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

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
        def moduleENTInstance = new ModuleENT(params)
        if (!moduleENTInstance.save(flush: true)) {
            render(view: "create", model: [moduleENTInstance: moduleENTInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), moduleENTInstance.id])
        redirect(action: "show", id: moduleENTInstance.id)
    }

    def show(Long id) {
        def moduleENTInstance = ModuleENT.get(id)
        if (!moduleENTInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), id])
            redirect(action: "list")
            return
        }

        [moduleENTInstance: moduleENTInstance]
    }

    def edit(Long id) {
        def moduleENTInstance = ModuleENT.get(id)
        if (!moduleENTInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), id])
            redirect(action: "list")
            return
        }

        [moduleENTInstance: moduleENTInstance]
    }

    def update(Long id, Long version) {
        def moduleENTInstance = ModuleENT.get(id)
        if (!moduleENTInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (moduleENTInstance.version > version) {
                moduleENTInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'moduleENT.label', default: 'ModuleENT')] as Object[],
                          "Another user has updated this ModuleENT while you were editing")
                render(view: "edit", model: [moduleENTInstance: moduleENTInstance])
                return
            }
        }

        moduleENTInstance.properties = params

        if (!moduleENTInstance.save(flush: true)) {
            render(view: "edit", model: [moduleENTInstance: moduleENTInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), moduleENTInstance.id])
        redirect(action: "show", id: moduleENTInstance.id)
    }

    def delete(Long id) {
        def moduleENTInstance = ModuleENT.get(id)
        if (!moduleENTInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), id])
            redirect(action: "list")
            return
        }

        try {
            moduleENTInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'moduleENT.label', default: 'ModuleENT'), id])
            redirect(action: "show", id: id)
        }
    }
}
