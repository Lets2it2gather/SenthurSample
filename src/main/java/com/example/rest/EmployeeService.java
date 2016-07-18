package com.example.rest;

import java.util.List;

interface EmployeeService {

	/**
	 * Creates a new employee entry.
	 * 
	 * @param employee
	 *            The information of the created employee entry.
	 * @return The information of the created employee entry.
	 */
	EmployeeDTO create(EmployeeDTO employee);

	/**
	 * Deletes a employee entry.
	 * 
	 * @param id
	 *            The id of the deleted employee entry.
	 * @return THe information of the deleted employee entry.
	 * @throws com.example.rest.EmployeeNotFoundException
	 *             if no employee entry is found.
	 */
	EmployeeDTO delete(String id);

	/**
	 * Finds all employee entries.
	 * 
	 * @return The information of all employee entries.
	 */
	List<EmployeeDTO> findAll();

	/**
	 * Finds a single employee entry.
	 * 
	 * @param id
	 *            The id of the requested employee entry.
	 * @return The information of the requested employee entry.
	 * @throws com.example.rest.EmployeeNotFoundException
	 *             if no employee entry is found.
	 */
	EmployeeDTO findById(String id);

	/**
	 * Updates the information of a employee entry.
	 * 
	 * @param employee
	 *            The information of the updated employee entry.
	 * @return The information of the updated employee entry.
	 * @throws com.example.rest.EmployeeNotFoundException
	 *             if no employee entry is found.
	 */
	EmployeeDTO update(EmployeeDTO employee);
}
