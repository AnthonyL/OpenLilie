package openlilie



import org.junit.*
import grails.test.mixin.*


@TestFor(DomainTestController)
@Mock(DomainTest)
class DomainTestControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/domainTest/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.domainTestInstanceList.size() == 0
        assert model.domainTestInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.domainTestInstance != null
    }

//    void testSave() {
//        controller.save()
//
//        assert model.domainTestInstance != null
//        assert view == '/domainTest/create'
//
//        response.reset()
//
//        populateValidParams(params)
//        controller.save()
//
//        assert response.redirectedUrl == '/domainTest/show/1'
//        assert controller.flash.message != null
//        assert DomainTest.count() == 1
//    }
//
//    void testShow() {
//        controller.show()
//
//        assert flash.message != null
//        assert response.redirectedUrl == '/domainTest/list'
//
//        populateValidParams(params)
//        def domainTest = new DomainTest(params)
//
//        assert domainTest.save() != null
//
//        params.id = domainTest.id
//
//        def model = controller.show()
//
//        assert model.domainTestInstance == domainTest
//    }
//
//    void testEdit() {
//        controller.edit()
//
//        assert flash.message != null
//        assert response.redirectedUrl == '/domainTest/list'
//
//        populateValidParams(params)
//        def domainTest = new DomainTest(params)
//
//        assert domainTest.save() != null
//
//        params.id = domainTest.id
//
//        def model = controller.edit()
//
//        assert model.domainTestInstance == domainTest
//    }
//
//    void testUpdate() {
//        controller.update()
//
//        assert flash.message != null
//        assert response.redirectedUrl == '/domainTest/list'
//
//        response.reset()
//
//        populateValidParams(params)
//        def domainTest = new DomainTest(params)
//
//        assert domainTest.save() != null
//
//        // test invalid parameters in update
//        params.id = domainTest.id
//        //TODO: add invalid values to params object
//
//        controller.update()
//
//        assert view == "/domainTest/edit"
//        assert model.domainTestInstance != null
//
//        domainTest.clearErrors()
//
//        populateValidParams(params)
//        controller.update()
//
//        assert response.redirectedUrl == "/domainTest/show/$domainTest.id"
//        assert flash.message != null
//
//        //test outdated version number
//        response.reset()
//        domainTest.clearErrors()
//
//        populateValidParams(params)
//        params.id = domainTest.id
//        params.version = -1
//        controller.update()
//
//        assert view == "/domainTest/edit"
//        assert model.domainTestInstance != null
//        assert model.domainTestInstance.errors.getFieldError('version')
//        assert flash.message != null
//    }
//
//    void testDelete() {
//        controller.delete()
//        assert flash.message != null
//        assert response.redirectedUrl == '/domainTest/list'
//
//        response.reset()
//
//        populateValidParams(params)
//        def domainTest = new DomainTest(params)
//
//        assert domainTest.save() != null
//        assert DomainTest.count() == 1
//
//        params.id = domainTest.id
//
//        controller.delete()
//
//        assert DomainTest.count() == 0
//        assert DomainTest.get(domainTest.id) == null
//        assert response.redirectedUrl == '/domainTest/list'
//    }
}
