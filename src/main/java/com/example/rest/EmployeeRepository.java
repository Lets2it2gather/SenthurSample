package com.example.rest;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface EmployeeRepository extends Repository<Employee, String> {

    /**
     * Deletes a employee entry from the database.
     * @param deleted   The deleted employee entry.
     */
    void delete(Employee deleted);

    /**
     * Finds all employee entries from the database.
     * @return  The information of all employee entries that are found from the database.
     */
    List<Employee> findAll();

    /**
     * Finds the information of a single employee entry.
     * @param id    The id of the requested employee entry.
     * @return      The information of the found employee entry. If no employee entry
     *              is found, this method returns an empty {@link java.util.Optional} object.
     */
    Optional<Employee> findOne(String id);

    /**
     * Saves a new employee entry to the database.
     * @param saved The information of the saved employee entry.
     * @return      The information of the saved employee entry.
     */
    Employee save(Employee saved);
}
