package openlilie

class DomainTest {

	String name;
    static constraints = {
		name blank:false, size:2..30;
    }
}
