package com.example.job.controller;

import com.example.job.entity.Employee;
import com.example.job.entity.Employer;
import com.example.job.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/employee/register")
    public Employee employeeRegister(@RequestBody Employee employee) {
        return authService.employeeRegister(employee);
    }

    @PostMapping("/employer/register")
    public Employer employerRegister(@RequestBody Employer employer) {
        return authService.employerRegister(employer);
    }

    @PostMapping("/employee/login")
    public Employee employeeLogin(@RequestBody Employee employee) {
        return authService.employeeLogin(employee);
    }

    @PostMapping("/employer/login")
    public Employer employerLogin(@RequestBody Employer employer) {
        return authService.employerLogin(employer);
    }

}
