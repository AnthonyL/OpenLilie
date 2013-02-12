package pages

import geb.Module

class ListPage extends ScaffoldPage {
	static url = "moduleENT/list"
	
	static at = {
		title ==~ /ModuleENT List/
	}
	
	static content = {
		newModuleENTButton(to: CreatePage) { $("a", text: "New ModuleENT") }
		modulesENTTable { $("div.content table", 0) }
		moduleENTRow { module moduleENTRow, moduleENTRows[it] }
		moduleENTRows(required: false) { modulesENTTable.find("tbody").find("tr") }
	}
}

class ModuleENTRow extends Module {
	static content = {
		cell { $("td", it) }
		cellText { cell(it).text() }
        cellHrefText{ cell(it).find('a').text() }
		title { cellText(0) }
		showLink(to: ShowPage) { cell(0).find("a") }
	}
}