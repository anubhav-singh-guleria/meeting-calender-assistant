package com.anubhav.calenderassistant.service.impl;

import com.anubhav.calenderassistant.model.Calender;
import com.anubhav.calenderassistant.model.Employee;
import com.anubhav.calenderassistant.repository.IEmployeeRepository;
import com.anubhav.calenderassistant.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;
    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public Employee findEmployeeById(long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void addCalenderToEmployee(Calender calender) {
        Employee employee = findEmployeeById(calender.getOwnerId());
        if(employee == null) {
            employee = new Employee();
            employee.setId(calender.getOwnerId());
            saveEmployee(employee);
        }
        employee.setCalender(calender);
        saveEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
