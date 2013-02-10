class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/login/$action?"(controller: "login")
		"/logout/$action?"(controller: "logout")
		"/"(controller:'login', action:'connection')
		"500"(view:'/error')
	}
}
