package com.example.employeebackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest {
    private String name;
    private String email;
    private String jobTitle;
    private String phone;
    private String imageUrl;
}
