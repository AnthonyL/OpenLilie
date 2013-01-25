package openlilie

import org.springframework.dao.DataIntegrityViolationException

class DomainTestController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [domainTestInstanceList: DomainTest.list(params), domainTestInstanceTotal: DomainTest.count()]
    }

    def create() {
        [domainTestInstance: new DomainTest(params)]
    }

    def save() {
        def domainTestInstance = new DomainTest(params)
        if (!domainTestInstance.save(flush: true)) {
            render(view: "create", model: [domainTestInstance: domainTestInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'domainTest.label', default: 'DomainTest'), domainTestInstance.id])
        redirect(action: "show", id: domainTestInstance.id)
    }

    def show(Long id) {
        def domainTestInstance = DomainTest.get(id)
        if (!domainTestInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'domainTest.label', default: 'DomainTest'), id])
            redirect(action: "list")
            return
        }

        [domainTestInstance: domainTestInstance]
    }

    def edit(Long id) {
        def domainTestInstance = DomainTest.get(id)
        if (!domainTestInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'domainTest.label', default: 'DomainTest'), id])
            redirect(action: "list")
            return
        }

        [domainTestInstance: domainTestInstance]
    }

    def update(Long id, Long version) {
        def domainTestInstance = DomainTest.get(id)
        if (!domainTestInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'domainTest.label', default: 'DomainTest'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (domainTestInstance.version > version) {
                domainTestInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'domainTest.label', default: 'DomainTest')] as Object[],
                          "Another user has updated this DomainTest while you were editing")
                render(view: "edit", model: [domainTestInstance: domainTestInstance])
                return
            }
        }

        domainTestInstance.properties = params

        if (!domainTestInstance.save(flush: true)) {
            render(view: "edit", model: [domainTestInstance: domainTestInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'domainTest.label', default: 'DomainTest'), domainTestInstance.id])
        redirect(action: "show", id: domainTestInstance.id)
    }

    def delete(Long id) {
        def domainTestInstance = DomainTest.get(id)
        if (!domainTestInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'domainTest.label', default: 'DomainTest'), id])
            redirect(action: "list")
            return
        }

        try {
            domainTestInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'domainTest.label', default: 'DomainTest'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'domainTest.label', default: 'DomainTest'), id])
            redirect(action: "show", id: id)
        }
    }
}
