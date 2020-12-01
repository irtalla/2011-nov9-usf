package exceptions;

public class NonExclusiveAuthorRole extends Exception {
	
	public NonExclusiveAuthorRole() {
		super("Persons with author role cannot take on another role");
	}

}
