package com.myFullstackAlharith.employee_management.controllers;

import com.myFullstackAlharith.employee_management.entities.Employee;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    ArrayList<Employee> employees = new ArrayList<>();


    // http://localhost:8080/employees
    @GetMapping
    public ResponseEntity<ArrayList<Employee>> findAll() {
        return new ResponseEntity<ArrayList<Employee>>(employees, HttpStatus.OK);
    }


    // http://localhost:8080/employees/b57f4434-8743-4b75-821a-05dd0fa64550
    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> findOne(@PathVariable UUID employeeId) {
        Optional<Employee> employee = employees.stream()
                .filter(i -> i.getId().equals(employeeId))
                .findFirst();

        if (employee.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID employeeId) {
        Optional<Employee> employee = employees.stream()
                .filter(i -> i.getId().equals(employeeId))
                .findFirst();

        employee.ifPresent(emp -> employees.remove(emp));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<Employee> updateOne(
            @PathVariable UUID employeeId,
            @RequestBody @Valid Employee employee) {
        Optional<Employee> existingEmployee = employees.stream()
                .filter(i -> i.getId().equals(employeeId))
                .findFirst();
        if (existingEmployee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Employee updatedEmployee = existingEmployee.get();
        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setEmail(employee.getEmail());
        updatedEmployee.setPhoneNumber(employee.getPhoneNumber());
        updatedEmployee.setPosition(employee.getPosition());
        updatedEmployee.setHireDate(employee.getHireDate());
        updatedEmployee.setDepartmentID(employee.getDepartmentID());

        return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);


    }


    @PostMapping
    public ResponseEntity<Employee> createOne(@RequestBody @Valid Employee employee) {
        employee.setId(UUID.randomUUID());
        employee.setDepartmentID(UUID.randomUUID());
        employees.add(employee);

        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }


}