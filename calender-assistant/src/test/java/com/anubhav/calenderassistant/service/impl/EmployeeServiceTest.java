package com.anubhav.calenderassistant.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.anubhav.calenderassistant.model.Calender;
import com.anubhav.calenderassistant.model.Employee;
import com.anubhav.calenderassistant.repository.IEmployeeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {EmployeeService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private IEmployeeRepository iEmployeeRepository;

    /**
     * Method under test: {@link EmployeeService#saveEmployee(Employee)}
     */
    @Test
    public void testSaveEmployee() {
        Calender calender = new Calender();
        calender.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender.setMeetings(new ArrayList<>());
        calender.setOwnerId(1L);
        calender.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Employee employee = new Employee();
        employee.setCalender(calender);
        employee.setId(1L);
        employee.setName("Name");
        when(iEmployeeRepository.save(Mockito.<Employee>any())).thenReturn(employee);

        Calender calender2 = new Calender();
        calender2.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender2.setMeetings(new ArrayList<>());
        calender2.setOwnerId(1L);
        calender2.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Employee employee2 = new Employee();
        employee2.setCalender(calender2);
        employee2.setId(1L);
        employee2.setName("Name");
        employeeService.saveEmployee(employee2);
        verify(iEmployeeRepository).save(Mockito.<Employee>any());
        assertEquals(calender, employee2.getCalender());
        assertEquals("Name", employee2.getName());
        assertEquals(1L, employee2.getId());
        assertTrue(employeeService.getAllEmployees().isEmpty());
    }

    /**
     * Method under test: {@link EmployeeService#deleteEmployee(Employee)}
     */
    @Test
    public void testDeleteEmployee() {
        doNothing().when(iEmployeeRepository).delete(Mockito.<Employee>any());

        Calender calender = new Calender();
        calender.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender.setMeetings(new ArrayList<>());
        calender.setOwnerId(1L);
        calender.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Employee employee = new Employee();
        employee.setCalender(calender);
        employee.setId(1L);
        employee.setName("Name");
        employeeService.deleteEmployee(employee);
        verify(iEmployeeRepository).delete(Mockito.<Employee>any());
        assertSame(calender, employee.getCalender());
        assertEquals("Name", employee.getName());
        assertEquals(1L, employee.getId());
        assertTrue(employeeService.getAllEmployees().isEmpty());
    }

    /**
     * Method under test: {@link EmployeeService#findEmployeeById(long)}
     */
    @Test
    public void testFindEmployeeById() {
        Calender calender = new Calender();
        calender.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender.setMeetings(new ArrayList<>());
        calender.setOwnerId(1L);
        calender.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Employee employee = new Employee();
        employee.setCalender(calender);
        employee.setId(1L);
        employee.setName("Name");
        Optional<Employee> ofResult = Optional.of(employee);
        when(iEmployeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(employee, employeeService.findEmployeeById(1L));
        verify(iEmployeeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployeeService#addCalenderToEmployee(Calender)}
     */
    @Test
    public void testAddCalenderToEmployee() {
        Calender calender = new Calender();
        calender.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender.setMeetings(new ArrayList<>());
        calender.setOwnerId(1L);
        calender.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Employee employee = new Employee();
        employee.setCalender(calender);
        employee.setId(1L);
        employee.setName("Name");

        Calender calender2 = new Calender();
        calender2.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender2.setMeetings(new ArrayList<>());
        calender2.setOwnerId(1L);
        calender2.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Employee employee2 = new Employee();
        employee2.setCalender(calender2);
        employee2.setId(1L);
        employee2.setName("Name");
        Optional<Employee> ofResult = Optional.of(employee2);
        when(iEmployeeRepository.save(Mockito.<Employee>any())).thenReturn(employee);
        when(iEmployeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Calender calender3 = new Calender();
        calender3.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender3.setMeetings(new ArrayList<>());
        calender3.setOwnerId(1L);
        calender3.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        employeeService.addCalenderToEmployee(calender3);
        verify(iEmployeeRepository).save(Mockito.<Employee>any());
        verify(iEmployeeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployeeService#addCalenderToEmployee(Calender)}
     */
    @Test
    public void testAddCalenderToEmployee3() {
        Calender calender = new Calender();
        calender.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender.setMeetings(new ArrayList<>());
        calender.setOwnerId(1L);
        calender.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Employee employee = new Employee();
        employee.setCalender(calender);
        employee.setId(1L);
        employee.setName("Name");
        when(iEmployeeRepository.save(Mockito.<Employee>any())).thenReturn(employee);
        when(iEmployeeRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        Calender calender2 = new Calender();
        calender2.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender2.setMeetings(new ArrayList<>());
        calender2.setOwnerId(1L);
        calender2.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        employeeService.addCalenderToEmployee(calender2);
        verify(iEmployeeRepository, atLeast(1)).save(Mockito.<Employee>any());
        verify(iEmployeeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployeeService#getAllEmployees()}
     */
    @Test
    public void testGetAllEmployees() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        when(iEmployeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> actualAllEmployees = employeeService.getAllEmployees();
        assertSame(employeeList, actualAllEmployees);
        assertTrue(actualAllEmployees.isEmpty());
        verify(iEmployeeRepository).findAll();
    }
}

