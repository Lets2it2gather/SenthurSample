package com.example.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
final class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	private final EmployeeService service;

	@Autowired
	EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	EmployeeDTO create(@RequestBody @Valid EmployeeDTO employeeEntry) {
		LOGGER.info("Creating a new employee entry with information: {}", employeeEntry);

		EmployeeDTO created = service.create(employeeEntry);
		LOGGER.info("Created a new employee entry with information: {}", created);

		return created;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	EmployeeDTO delete(@PathVariable("id") String id) {
		LOGGER.info("Deleting employee entry with id: {}", id);

		EmployeeDTO deleted = service.delete(id);
		LOGGER.info("Deleted employee entry with information: {}", deleted);

		return deleted;
	}

	@RequestMapping(method = RequestMethod.GET)
	List<EmployeeDTO> findAll() {
		LOGGER.info("Finding all employee entries");

		List<EmployeeDTO> employeeEntries = service.findAll();
		LOGGER.info("Found {} employee entries", employeeEntries.size());

		return employeeEntries;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	EmployeeDTO findById(@PathVariable("id") String id) {
		LOGGER.info("Finding employee entry with id: {}", id);

		EmployeeDTO employeeEntry = service.findById(id);
		LOGGER.info("Found employee entry with information: {}", employeeEntry);

		return employeeEntry;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	EmployeeDTO update(@RequestBody @Valid EmployeeDTO employeeEntry) {
		LOGGER.info("Updating employee entry with information: {}", employeeEntry);

		EmployeeDTO updated = service.update(employeeEntry);
		LOGGER.info("Updated employee entry with information: {}", updated);

		return updated;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleemployeeNotFound(EmployeeNotFoundException ex) {
		LOGGER.error("Handling error with message: {}", ex.getMessage());
	}
}
