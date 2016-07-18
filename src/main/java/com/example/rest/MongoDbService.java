package com.example.rest;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
final class MongoDbService implements EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbService.class);

	private final EmployeeRepository repository;

	@Autowired
	MongoDbService(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Override
	public EmployeeDTO create(EmployeeDTO employee) {
		LOGGER.info("Creating a new employee entry with information: {}", employee);

		Employee persisted = Employee.getBuilder().title(employee.getTitle()).name(employee.getName()).build();

		persisted = repository.save(persisted);
		LOGGER.info("Created a new employee entry with information: {}", persisted);

		return convertToDTO(persisted);
	}

	@Override
	public EmployeeDTO delete(String id) {
		LOGGER.info("Deleting a employee entry with id: {}", id);

		Employee deleted = findEmployeeById(id);
		repository.delete(deleted);

		LOGGER.info("Deleted employee entry with informtation: {}", deleted);

		return convertToDTO(deleted);
	}

	@Override
	public List<EmployeeDTO> findAll() {
		LOGGER.info("Finding all employee entries.");

		List<Employee> employeeEntries = repository.findAll();

		LOGGER.info("Found {} employee entries", employeeEntries.size());

		return convertToDTOs(employeeEntries);
	}

	private List<EmployeeDTO> convertToDTOs(List<Employee> models) {
		return models.stream().map(this::convertToDTO).collect(toList());
	}

	@Override
	public EmployeeDTO findById(String id) {
		LOGGER.info("Finding employee entry with id: {}", id);

		Employee found = findEmployeeById(id);

		LOGGER.info("Found employee entry: {}", found);

		return convertToDTO(found);
	}

	@Override
	public EmployeeDTO update(EmployeeDTO employee) {
		LOGGER.info("Updating employee entry with information: {}", employee);

		Employee updated = findEmployeeById(employee.getId());
		updated.update(employee.getTitle(), employee.getName());
		updated = repository.save(updated);

		LOGGER.info("Updated employee entry with information: {}", updated);

		return convertToDTO(updated);
	}

	private Employee findEmployeeById(String id) {
		Optional<Employee> result = repository.findOne(id);
		return result.orElseThrow(() -> new EmployeeNotFoundException(id));

	}

	private EmployeeDTO convertToDTO(Employee model) {
		EmployeeDTO dto = new EmployeeDTO();

		dto.setId(model.getId());
		dto.setTitle(model.getTitle());
		dto.setName(model.getName());

		return dto;
	}
}
