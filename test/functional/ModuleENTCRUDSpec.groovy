import geb.spock.GebReportingSpec

import spock.lang.*

import pages.*

@Stepwise
class ModuleENTCRUDSpec extends GebReportingSpec {
	
	def "there are no Modules ENT"() {
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
		title = "ModuleENT"
		createButton.click()
		then:
		at ShowPage
	}
	
	def "check the entered details"() {
		expect:
		title == "ModuleENT"
	}

	def "edit the details"() {
		when:
		editButton.click()
		then:
		at EditPage
		when:
		enabled = false
		updateButton.click()
		then:
		at ShowPage
	}
	
	def "check in listing"() {
		when:
		to ListPage
		then:
		moduleENTRows.size() == 3
		def row = moduleENTRow(0)
		row.title == "ModuleENT"
	}
	
	def "show moduleENT"() {
		when:
		moduleENTRow(0).showLink.click()
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