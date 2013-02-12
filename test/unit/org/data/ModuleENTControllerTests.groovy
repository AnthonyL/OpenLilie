package org.data



import org.junit.*
import grails.test.mixin.*

@TestFor(ModuleENTController)
@Mock(ModuleENT)
class ModuleENTControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/moduleENT/list" == response.redirectedUrl
    }

//    void testList() {
//
//        def model = controller.list()
//
//        assert model.moduleENTInstanceList.size() == 0
//        assert model.moduleENTInstanceTotal == 0
//    }

    void testCreate() {
        def model = controller.create()

        assert model.moduleENTInstance != null
    }

	/*
    void testSave() {
        controller.save()

        assert model.moduleENTInstance != null
        assert view == '/moduleENT/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/moduleENT/show/1'
        assert controller.flash.message != null
        assert ModuleENT.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/moduleENT/list'

        populateValidParams(params)
        def moduleENT = new ModuleENT(params)

        assert moduleENT.save() != null

        params.id = moduleENT.id

        def model = controller.show()

        assert model.moduleENTInstance == moduleENT
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/moduleENT/list'

        populateValidParams(params)
        def moduleENT = new ModuleENT(params)

        assert moduleENT.save() != null

        params.id = moduleENT.id

        def model = controller.edit()

        assert model.moduleENTInstance == moduleENT
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/moduleENT/list'

        response.reset()

        populateValidParams(params)
        def moduleENT = new ModuleENT(params)

        assert moduleENT.save() != null

        // test invalid parameters in update
        params.id = moduleENT.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/moduleENT/edit"
        assert model.moduleENTInstance != null

        moduleENT.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/moduleENT/show/$moduleENT.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        moduleENT.clearErrors()

        populateValidParams(params)
        params.id = moduleENT.id
        params.version = -1
        controller.update()

        assert view == "/moduleENT/edit"
        assert model.moduleENTInstance != null
        assert model.moduleENTInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/moduleENT/list'

        response.reset()

        populateValidParams(params)
        def moduleENT = new ModuleENT(params)

        assert moduleENT.save() != null
        assert ModuleENT.count() == 1

        params.id = moduleENT.id

        controller.delete()

        assert ModuleENT.count() == 0
        assert ModuleENT.get(moduleENT.id) == null
        assert response.redirectedUrl == '/moduleENT/list'
    }*/
}
