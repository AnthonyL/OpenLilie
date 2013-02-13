package pages

import geb.Page

class LoginPage extends Page {

	static url = "login/auth"

    static at = { 
		title == "ModuleENT List" 
		}

    static content = {
       loginForm { $("form") }
       loginButton { $("input", value: "Login") }
    }

}
