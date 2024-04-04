package com.anubhav.calenderassistant.controller;

import com.anubhav.calenderassistant.model.Calender;
import com.anubhav.calenderassistant.model.Employee;
import com.anubhav.calenderassistant.model.FreeSlotDAO;
import com.anubhav.calenderassistant.model.Meeting;
import com.anubhav.calenderassistant.service.ICalenderService;
import com.anubhav.calenderassistant.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class CalenderAssistantController {
    @Autowired
    private ICalenderService calenderService;

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("/addEmployee")
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
    }

    @PostMapping("/addCalenderToEmployee")
    public void addCalenderToEmployee(@RequestBody Calender calender) {
        employeeService.addCalenderToEmployee(calender);
    }

    @GetMapping("/getFreeSlots")
    public List<LocalDateTime> getFreeSlots(@RequestBody FreeSlotDAO freeSlotDAO) {
        return calenderService.findFreeSlots(freeSlotDAO.getEmployeeId1(), freeSlotDAO.getEmployeeId2(), freeSlotDAO.getMeetingDuration());
    }

    @GetMapping("/getMeetingConflicts")
    public List<Employee> getMeetingConflicts(@RequestBody Meeting meeting) {
        return calenderService.findMeetingConflicts(meeting);
    }
}
