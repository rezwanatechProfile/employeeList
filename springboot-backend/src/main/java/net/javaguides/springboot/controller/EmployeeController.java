package net.javaguides.springboot.controller;


import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

//    @Autowired is used to inject the EmployeeRepository bean into the controller. This repository is likely responsible for interacting with the database to perform CRUD (Create, Read, Update, Delete) operations on Employee entities.

    @GetMapping
    public List<Employee> getAllEmployees() {
//        @GetMapping is used for handling HTTP GET requests to the "/api/v1/employees" endpoint, returning a list of all employees retrieved from the repository.
        return employeeRepository.findAll();
    }

//    build create employee REST API
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
//        @RequestBody, which means it expects the request body to contain a representation of the Employee object. This data is then automatically deserialized into the Employee object by Spring.
        return employeeRepository.save(employee);
    }

//    build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
//        @GetMapping annotation and a @PathVariable to extract the id from the URI.
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id: "+ id));
        return ResponseEntity.ok(employee);
//        If the Employee is found, this line returns a ResponseEntity with a status code of 200 (OK) and the found Employee object as the response body.

    }

    // build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) {
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    // build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
