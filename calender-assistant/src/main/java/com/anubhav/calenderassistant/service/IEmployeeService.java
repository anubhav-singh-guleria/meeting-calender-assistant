package com.anubhav.calenderassistant.service;

import com.anubhav.calenderassistant.model.Calender;
import com.anubhav.calenderassistant.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEmployeeService {
    void saveEmployee(Employee employee);
    void deleteEmployee(Employee employee);
    Employee findEmployeeById(long id);
    void addCalenderToEmployee(Calender calender);
    List<Employee> getAllEmployees();

}
