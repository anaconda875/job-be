package com.example.job.service;

import com.example.job.entity.Employee;
import com.example.job.entity.Employer;
import com.example.job.entity.User;
import com.example.job.exception.ResourceNotFoundException;
import com.example.job.repository.EmployeeRepository;
import com.example.job.repository.EmployerRepository;
import com.example.job.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final SecureRandom RANDOM = new SecureRandom();

    private final EmployeeRepository employeeRepository;
    private final EmployerRepository employerRepository;
    private final UserRepository userRepository;

    public Employee employeeRegister(Employee employee) {
        String pin = "";
        for(int i = 0; i < 6; i++) {
            pin += RANDOM.nextInt(10);
        }

        employee.setPin(pin);
        return employeeRepository.save(employee);
    }

    public Employer employerRegister(Employer employer) {
        String pin = "";
        for(int i = 0; i < 6; i++) {
            pin += RANDOM.nextInt(10);
        }

        employer.setPin(pin);
        return employerRepository.save(employer);
    }

    public Employee employeeLogin(Employee employee) {
        return employeeRepository.findByIdAndPin(employee.getId(), employee.getPin()).orElseThrow(ResourceNotFoundException::new);
    }

    public Employer employerLogin(Employer employer) {
        return employerRepository.findByIdAndPin(employer.getId(), employer.getPin()).orElseThrow(ResourceNotFoundException::new);
    }

    public User userLogin(User user) {
        return userRepository.findByUsernameAndPwd(user.getUsername(), user.getPwd()).orElseThrow(ResourceNotFoundException::new);
    }

}
