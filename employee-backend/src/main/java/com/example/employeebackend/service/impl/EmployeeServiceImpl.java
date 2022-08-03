package com.example.employeebackend.service.impl;

import com.example.employeebackend.dto.request.NewEmployeeRequest;
import com.example.employeebackend.dto.request.UpdateEmployeeRequest;
import com.example.employeebackend.dto.response.GenericEmployeeResponse;
import com.example.employeebackend.exception.ResourceNotFoundException;
import com.example.employeebackend.model.Employee;
import com.example.employeebackend.repository.EmployeeRepository;
import com.example.employeebackend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    private static ResourceNotFoundException getResourceNotFoundException(Long employeeId) {
        return new ResourceNotFoundException("Employee not found! ::" + employeeId);
    }

    @Override
    public List<GenericEmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> modelMapper.map(employee, GenericEmployeeResponse.class))
                .toList();
    }

    @Override
    public GenericEmployeeResponse getEmployeeById(Long employeeId) throws ResourceNotFoundException {
        return modelMapper.map(employeeRepository.findById(employeeId).orElseThrow(
                () -> getResourceNotFoundException(employeeId)), GenericEmployeeResponse.class);


    }


    @Override
    public GenericEmployeeResponse createNewEmployee(NewEmployeeRequest employee) {
        try {
            var createEmployee = employeeRepository.save(modelMapper.map(employee, Employee.class));
            return modelMapper.map(createEmployee, GenericEmployeeResponse.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public GenericEmployeeResponse updateEmployee(UpdateEmployeeRequest request) throws ResourceNotFoundException {
        var updateEmployee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(
                () -> getResourceNotFoundException(request.getEmployeeId()));
        modelMapper.map(request, updateEmployee);
        return modelMapper.map(employeeRepository.saveAndFlush(updateEmployee), GenericEmployeeResponse.class);
    }

    @Override
    public Map<String, Boolean> deleteEmployee(Long employeeId) throws ResourceNotFoundException {
        var employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> getResourceNotFoundException(employeeId));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
