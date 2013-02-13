import geb.spock.GebReportingSpec

import spock.lang.*

import pages.*

@Stepwise
class ModuleENTCRUDSpec extends GebReportingSpec {
	
	def "admin login"() {
       given : "I am at the login page"
       to LoginPage

       when: "I am entering valid username and password"
       loginForm.j_username = "anthony"
       loginForm.j_password = "pass"
       loginButton.click()

       then: "I am being redirected to the admin homepage"
       at ListPage
    }
	
	def "there are 2 Modules ENT"() {
		when:
		to ListPage
		then:
		moduleENTRows.size() == 2
	}
	
	def "add a moduleENT"() {
		when:
		newModuleENTButton.click()
		then:
		at CreatePage
	}
	
	def "enter the details"() {
		when:
		title2 = "ModuleENT"
		createButton.click()
		then:
		at ShowPage
	}
	
	def "check the entered details"() {
		expect:
		title2 == "ModuleENT"
	}

	def "edit the details"() {
		when:
		editButton.click()
		then:
		at EditPage
		when:
		title2 = "ModuleENT"
		updateButton.click()
		then:
		at ShowPage
	}
	
	def "check in listing"() {
		when:
		to ListPage
		then:
		moduleENTRows.size() == 3
		def row = moduleENTRow(2)
		row.title2 == "ModuleENT"
	}
	
	def "show moduleENT"() {
		when:
		moduleENTRow(2).showLink.click()
		then:
		at ShowPage
	}
	
	def "delete moduleENT"() {
		given:
		def deletedId = id
		when:
		withConfirm { deleteButton.click() }
		then:
		at ListPage
		message == "ModuleENT $deletedId deleted"
		moduleENTRows.size() == 2
	}
}