class UrlMappings {

    static excludes = ["/fonts/*","/img/*","/theme/*","/bower_components/*","/i18n/*", "/css/*"]

    static mappings = {
        group("/api") {
            "/patient" (controller: "apiPatient", parseRequest: false) {
                action = [GET: "list", POST: "create"]
            }
            "/activate/${token}" (controller: "apiPatient", parseRequest: false) {
                action = [GET: "activate"]
            }
        }

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
