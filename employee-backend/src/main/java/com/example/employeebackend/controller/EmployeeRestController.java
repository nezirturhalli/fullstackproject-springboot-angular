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
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<GenericEmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<GenericEmployeeResponse> getEmployeeById(@PathVariable(value = "employeeId") Long employeeId) throws ResourceNotFoundException {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @PostMapping
    public GenericEmployeeResponse createEmployee(@Valid @RequestBody NewEmployeeRequest employee) {
        return employeeService.createNewEmployee(employee);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<GenericEmployeeResponse> updateEmployee(@PathVariable(value = "employeeId") Long employeeId,
                                                   @Valid @RequestBody UpdateEmployeeRequest employeeDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, employeeDetails));
    }

    @DeleteMapping("/{employeeId}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "employeeId") Long employeeId) throws ResourceNotFoundException {
        return employeeService.deleteEmployee(employeeId);
    }


}
