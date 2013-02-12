import geb.junit4.GebReportingTest

import pages.*
import org.junit.Test

class ModuleENTCRUDTests extends GebReportingTest {

	@Test
	void doSomeCrud() {
		to ListPage
		assert moduleENTRows.size() == 2
		newModuleENTButton.click()
		
		assert at(CreatePage)
		title = "moduleENT"
		createButton.click()
		
		assert at(ShowPage)
		assert title == "moduleENT"
		editButton.click()
		
		assert at(EditPage)
		title = "moduleENT"
		updateButton.click()
		
		assert at(ShowPage)
		
		to ListPage
		assert moduleENTRows.size() == 3
		def row = moduleENTRow(0)
		assert row.title == "moduleENT"
		row.showLink.click()
		
		assert at(ShowPage)
		def deletedId = id
		withConfirm { deleteButton.click() }
		
		assert at(ListPage)
		assert message == "ModuleENT $deletedId deleted"
		assert moduleENTRows.size() == 2
	}
}