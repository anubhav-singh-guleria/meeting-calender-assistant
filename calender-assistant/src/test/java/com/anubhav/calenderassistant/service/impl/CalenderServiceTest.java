package com.anubhav.calenderassistant.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.anubhav.calenderassistant.model.Calender;
import com.anubhav.calenderassistant.model.Employee;
import com.anubhav.calenderassistant.model.Meeting;
import com.anubhav.calenderassistant.service.strategy.MeetingConflictStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;

import org.junit.Rule;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {CalenderService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CalenderServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private CalenderService calenderService;

    @MockBean
    private EmployeeService employeeService;

    @MockBean(name = "BasicConflictStrategy")
    private MeetingConflictStrategy meetingConflictStrategy;

    /**
     * Method under test: {@link CalenderService#findFreeSlots(long, long, int)}
     */
    @Test
    public void testFindFreeSlots() {
        Calender calender = new Calender();
        calender.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender.setMeetings(new ArrayList<>());
        calender.setOwnerId(1L);
        calender.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Employee employee = new Employee();
        employee.setCalender(calender);
        employee.setId(1L);
        employee.setName("Name");
        when(employeeService.findEmployeeById(anyLong())).thenReturn(employee);
        assertTrue(calenderService.findFreeSlots(1L, 1L, 1).isEmpty());
        verify(employeeService, atLeast(1)).findEmployeeById(anyLong());
    }

    /**
     * Method under test: {@link CalenderService#findFreeSlots(long, long, int)}
     */
    @Test
    public void testFindFreeSlots2() {
        Calender calender = new Calender();
        calender.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender.setMeetings(new ArrayList<>());
        calender.setOwnerId(1L);
        calender.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Calender calender2 = mock(Calender.class);
        when(calender2.getMeetings()).thenThrow(new IllegalArgumentException("foo"));
        when(calender2.getStartDateTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        doNothing().when(calender2).setEndDateTime(Mockito.<LocalDateTime>any());
        doNothing().when(calender2).setMeetings(Mockito.<List<Meeting>>any());
        doNothing().when(calender2).setOwnerId(anyLong());
        doNothing().when(calender2).setStartDateTime(Mockito.<LocalDateTime>any());
        calender2.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender2.setMeetings(new ArrayList<>());
        calender2.setOwnerId(1L);
        calender2.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Employee employee = mock(Employee.class);
        when(employee.getCalender()).thenReturn(calender2);
        doNothing().when(employee).setCalender(Mockito.<Calender>any());
        doNothing().when(employee).setId(anyLong());
        doNothing().when(employee).setName(Mockito.<String>any());
        employee.setCalender(calender);
        employee.setId(1L);
        employee.setName("Name");
        when(employeeService.findEmployeeById(anyLong())).thenReturn(employee);
        thrown.expect(IllegalArgumentException.class);
        calenderService.findFreeSlots(1L, 1L, 1);
        verify(employeeService, atLeast(1)).findEmployeeById(anyLong());
        verify(employee).getCalender();
        verify(employee).setCalender(Mockito.<Calender>any());
        verify(employee).setId(anyLong());
        verify(employee).setName(Mockito.<String>any());
        verify(calender2).getStartDateTime();
        verify(calender2).getMeetings();
        verify(calender2).setEndDateTime(Mockito.<LocalDateTime>any());
        verify(calender2).setMeetings(Mockito.<List<Meeting>>any());
        verify(calender2).setOwnerId(anyLong());
        verify(calender2).setStartDateTime(Mockito.<LocalDateTime>any());
    }


    /**
     * Method under test: {@link CalenderService#findMeetingConflicts(Meeting)}
     */
    @Test
    public void testFindMeetingConflicts() {
        when(employeeService.getAllEmployees()).thenReturn(new ArrayList<>());
        assertTrue(calenderService.findMeetingConflicts(new Meeting()).isEmpty());
        verify(employeeService).getAllEmployees();
    }

    /**
     * Method under test: {@link CalenderService#findMeetingConflicts(Meeting)}
     */
    @Test
    public void testFindMeetingConflicts2() {
        Calender calender = new Calender();
        calender.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender.setMeetings(new ArrayList<>());
        calender.setOwnerId(1L);
        calender.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Employee employee = new Employee();
        employee.setCalender(calender);
        employee.setId(1L);
        employee.setName("Name");

        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        assertTrue(calenderService.findMeetingConflicts(new Meeting()).isEmpty());
        verify(employeeService).getAllEmployees();
    }

    /**
     * Method under test: {@link CalenderService#findMeetingConflicts(Meeting)}
     */
    @Test
    public void testFindMeetingConflicts3() {
        when(employeeService.getAllEmployees()).thenThrow(new IllegalArgumentException("foo"));
        thrown.expect(IllegalArgumentException.class);
        calenderService.findMeetingConflicts(new Meeting());
        verify(employeeService).getAllEmployees();
    }


    /**
     * Method under test: {@link CalenderService#findMeetingConflicts(Meeting)}
     */
    @Test
    public void testFindMeetingConflicts5() {
        Calender calender = new Calender();
        calender.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender.setMeetings(new ArrayList<>());
        calender.setOwnerId(1L);
        calender.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Meeting meeting = mock(Meeting.class);
        when(meeting.overlaps(Mockito.<Meeting>any())).thenReturn(true);

        ArrayList<Meeting> meetings = new ArrayList<>();
        meetings.add(meeting);

        Calender calender2 = new Calender();
        calender2.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender2.setMeetings(meetings);
        calender2.setOwnerId(1L);
        calender2.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Employee employee = mock(Employee.class);
        when(employee.getCalender()).thenReturn(calender2);
        doNothing().when(employee).setCalender(Mockito.<Calender>any());
        doNothing().when(employee).setId(anyLong());
        doNothing().when(employee).setName(Mockito.<String>any());
        employee.setCalender(calender);
        employee.setId(1L);
        employee.setName("Name");

        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        assertEquals(1, calenderService.findMeetingConflicts(new Meeting()).size());
        verify(employeeService).getAllEmployees();
        verify(employee).getCalender();
        verify(employee).setCalender(Mockito.<Calender>any());
        verify(employee).setId(anyLong());
        verify(employee).setName(Mockito.<String>any());
        verify(meeting).overlaps(Mockito.<Meeting>any());
    }

    /**
     * Method under test: {@link CalenderService#findMeetingConflicts(Meeting)}
     */
    @Test
    public void testFindMeetingConflicts6() {
        Calender calender = new Calender();
        calender.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender.setMeetings(new ArrayList<>());
        calender.setOwnerId(1L);
        calender.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Meeting meeting = mock(Meeting.class);
        when(meeting.overlaps(Mockito.<Meeting>any())).thenReturn(false);

        ArrayList<Meeting> meetings = new ArrayList<>();
        meetings.add(meeting);

        Calender calender2 = new Calender();
        calender2.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calender2.setMeetings(meetings);
        calender2.setOwnerId(1L);
        calender2.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Employee employee = mock(Employee.class);
        when(employee.getCalender()).thenReturn(calender2);
        doNothing().when(employee).setCalender(Mockito.<Calender>any());
        doNothing().when(employee).setId(anyLong());
        doNothing().when(employee).setName(Mockito.<String>any());
        employee.setCalender(calender);
        employee.setId(1L);
        employee.setName("Name");

        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        assertTrue(calenderService.findMeetingConflicts(new Meeting()).isEmpty());
        verify(employeeService).getAllEmployees();
        verify(employee).getCalender();
        verify(employee).setCalender(Mockito.<Calender>any());
        verify(employee).setId(anyLong());
        verify(employee).setName(Mockito.<String>any());
        verify(meeting).overlaps(Mockito.<Meeting>any());
    }
}

