package com.example.rest;


public class EmployeeNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String id) {
        super(String.format("No employee entry found with id: <%s>", id));
    }
}
