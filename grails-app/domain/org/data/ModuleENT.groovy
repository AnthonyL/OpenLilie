package org.data

class ModuleENT {

	String title;
	User createur;
	User projectOwner;
	
    static constraints = {
		title (unique : true, nullable : false, size:2..30, blank : false)
		createur (nullable : true)
		projectOwner (nullable : true)
    }
	
	@Override
	public String toString() {
		return title
	}
}
