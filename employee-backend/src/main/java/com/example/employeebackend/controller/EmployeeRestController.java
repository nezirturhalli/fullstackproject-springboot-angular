package com.example.employeebackend.controller;


import com.example.employeebackend.dto.request.NewEmployeeRequest;
import com.example.employeebackend.dto.request.UpdateEmployeeRequest;
import com.example.employeebackend.dto.response.GenericEmployeeResponse;
import com.example.employeebackend.exception.ResourceNotFoundException;
import com.example.employeebackend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestScope
@RequestMapping("employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping("/all")
    public List<GenericEmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<GenericEmployeeResponse> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @PostMapping("/add")
    public GenericEmployeeResponse createEmployee(@Valid @RequestBody NewEmployeeRequest employee) {
        return employeeService.createNewEmployee(employee);
    }

    @PutMapping("/update")
    public ResponseEntity<GenericEmployeeResponse> updateEmployee(
            @Valid @RequestBody UpdateEmployeeRequest employeeDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeDetails));
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        return employeeService.deleteEmployee(employeeId);
    }


}
