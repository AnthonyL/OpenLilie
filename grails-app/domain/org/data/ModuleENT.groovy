package org.data

class ModuleENT {

	String title;
	User createur;
	User projectOwner;
	
    static constraints = {
		title (unique : true, size:2..30, blank : false)
		createur (nullable : true)
		projectOwner (nullable : true)
    }
}
