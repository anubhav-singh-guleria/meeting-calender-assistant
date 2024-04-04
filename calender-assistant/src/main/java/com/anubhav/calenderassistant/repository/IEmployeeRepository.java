package com.anubhav.calenderassistant.repository;

import com.anubhav.calenderassistant.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends CRUDRepository<Employee, Long>{

}
