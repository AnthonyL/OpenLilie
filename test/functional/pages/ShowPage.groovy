package pages

class ShowPage extends ScaffoldPage {

	static at = {
		heading.text() ==~ /Show ModuleENT/
	}
	
	static content = {
		editButton(to: EditPage) { $("a", text: "Edit") }
		deleteButton(to: ListPage) { $("input", value: "Delete") }
		row { $("li.fieldcontain span.property-label", text: it).parent() }
		value { row(it).find("span.property-value").text() }
		title2 { value("Title") }
		
	}
}
