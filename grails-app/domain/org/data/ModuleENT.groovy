package org.data

class ModuleENT {

	String title2;
	User createur;
	User projectOwner;
	
    static constraints = {
		title2 (unique : true, size:2..30, blank : false)
		createur (nullable : true)
		projectOwner (nullable : true)
    }
	
	@Override
	public String toString() {
		return title2
	}
}
