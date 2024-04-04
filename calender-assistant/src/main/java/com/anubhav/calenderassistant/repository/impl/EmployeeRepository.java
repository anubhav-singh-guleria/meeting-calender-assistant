package com.anubhav.calenderassistant.repository.impl;

import com.anubhav.calenderassistant.model.Employee;
import com.anubhav.calenderassistant.repository.IEmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class EmployeeRepository implements IEmployeeRepository {

    private final Map<Long, Employee> employeeMap;

    public EmployeeRepository(Map<Long, Employee> employeeMap) {
        this.employeeMap = employeeMap;
    }

    public EmployeeRepository() {
        this.employeeMap = new HashMap<>();
    }

    @Override
    public Employee save(Employee entity) {
        employeeMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Employee> findAll() {
        return employeeMap.values().stream().toList();
    }

    @Override
    public Optional<Employee> findById(Long aLong) {
        return Optional.ofNullable(employeeMap.get(aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        return employeeMap.containsKey(aLong);
    }

    @Override
    public void delete(Employee entity) {
        employeeMap.remove(entity.getId());
    }

    @Override
    public void deleteById(Long aLong) {
        employeeMap.remove(aLong);
    }

    @Override
    public long count() {
        return employeeMap.size();
    }
}
