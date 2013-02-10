package org.data

import grails.test.mixin.*
import org.junit.*
import org.security.Authority
import org.security.AuthPersonAuthority


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ModuleENT)
class ModuleENTTests {

    def void testCreate() {
       def userCreator = new User(username:"creator", password:"pass", fullName:"creatorName", enabled:true)
	   def userProjectOwner = new User(username:"projectOwner", password:"pass", fullName:"projectOwnerName", enabled:true)
	   //def governorRole = new Authority(authority: "ROLE_GOVERNOR")
	   //def projectOwnerRole = new Authority(authority: "ROLE_PROJECTOWNER")
	   //AuthPersonAuthority.create userCreator, governorRole
	   //AuthPersonAuthority.create userProjectOwner, projectOwnerRole
	   
	   def moduleENTtest = new ModuleENT(title: "module ENT test", createur: userCreator, projectOwner: userProjectOwner)
	   assertTrue(moduleENTtest.title.equals("module ENT test"))
	   assertTrue(moduleENTtest.createur.username.equals("creator"))
	   assertTrue(moduleENTtest.projectOwner.username.equals("projectOwner"))
	   //assertTrue(moduleENTtest.createur.getAuthorities().equals("ROLE_GOVERNOR"))
	   //assertTrue(moduleENTtest.projectOwner.getAuthorities().equals("ROLE_PROJECTOWNER"))
    }
}
