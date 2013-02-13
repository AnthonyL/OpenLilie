import org.data.ModuleENT
import org.data.User
import org.security.Authority
import org.security.AuthPersonAuthority

class BootStrap {

    def init = { servletContext ->
		def governorRole = new Authority(authority: "ROLE_GOVERNOR").save(failOnError:true)
		def projectOwnerRole = new Authority(authority: "ROLE_PROJECTOWNER").save(failOnError:true)
		def projectUserRole = new Authority(authority: "ROLE_PROJECTUSER").save(failOnError:true)
		
		def anthonyUser = new User(username:"anthony", password:"pass", enabled:true, fullName:"Anthony Letourneur").save(failOnError:true)
		def thierryUser = new User(username:"thierry", password:"pass", enabled:true, fullName:"Thierry Weissbeck").save(failOnError:true)
		def sebUser = new User(username:"seb", password:"pass", enabled:true, fullName:"Sebastien Chapeyroux").save(failOnError:true)
		def loisUser = new User(username:"lois", password:"pass", enabled:true, fullName:"Loïs Collet").save(failOnError:true)
		
		AuthPersonAuthority.create anthonyUser, governorRole
		AuthPersonAuthority.create thierryUser, projectOwnerRole
		AuthPersonAuthority.create sebUser, projectOwnerRole
		AuthPersonAuthority.create loisUser, projectUserRole
		
		def moduleENT1 = new ModuleENT(title2: "module ENT 1", createur: anthonyUser, projectOwner: thierryUser).save(failOnError:true)
		def moduleENT2 = new ModuleENT(title2: "module ENT 2", createur: anthonyUser, projectOwner: thierryUser).save(failOnError:true)
    }
    def destroy = {
    }
}
