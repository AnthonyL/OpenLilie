import geb.junit4.GebReportingTest

import pages.*
import org.junit.Test

class ModuleENTCRUDTests extends GebReportingTest {

	@Test
	void doSomeCrud() {
		
		to LoginPage
		loginForm.j_username = "anthony"
		loginForm.j_password = "pass"
		loginButton.click()
		
		to ListPage
		assert moduleENTRows.size() == 2
		newModuleENTButton.click()
		
		assert at(CreatePage)
		title2 = "moduleENT"
		createButton.click()
		
		assert at(ShowPage)
		assert title2 == "moduleENT"
		editButton.click()
		
		assert at(EditPage)
		title2 = "moduleENT"
		updateButton.click()
		
		assert at(ShowPage)
		
		to ListPage
		assert moduleENTRows.size() == 3
		def row = moduleENTRow(2)
		assert row.title2 == "moduleENT"
		row.showLink.click()
		
		assert at(ShowPage)
		def deletedId = id
		withConfirm { deleteButton.click() }
		
		assert at(ListPage)
		assert message == "ModuleENT $deletedId deleted"
		assert moduleENTRows.size() == 2
	}
}