package com.example.employeebackend.service;

import com.example.employeebackend.dto.request.NewEmployeeRequest;
import com.example.employeebackend.dto.request.UpdateEmployeeRequest;
import com.example.employeebackend.dto.response.GenericEmployeeResponse;
import com.example.employeebackend.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<GenericEmployeeResponse> getAllEmployees();
    GenericEmployeeResponse getEmployeeById(Long employeeId) throws ResourceNotFoundException;

    GenericEmployeeResponse createNewEmployee(NewEmployeeRequest employee);

    GenericEmployeeResponse updateEmployee(Long employeeId, UpdateEmployeeRequest employeeDetails) throws ResourceNotFoundException;

    Map<String, Boolean> deleteEmployee(Long employeeId) throws ResourceNotFoundException;


}
