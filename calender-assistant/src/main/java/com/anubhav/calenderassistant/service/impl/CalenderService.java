package com.anubhav.calenderassistant.service.impl;

import com.anubhav.calenderassistant.model.Calender;
import com.anubhav.calenderassistant.model.Employee;
import com.anubhav.calenderassistant.model.Meeting;
import com.anubhav.calenderassistant.service.ICalenderService;
import com.anubhav.calenderassistant.service.strategy.MeetingConflictStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalenderService implements ICalenderService {
    @Autowired
    @Qualifier("BasicConflictStrategy")
    private MeetingConflictStrategy meetingConflictStrategy;
    @Autowired
    private EmployeeService employeeService;


    @Override
    public List<LocalDateTime> findFreeSlots(long employeeId1, long employeeId2, int meetingDuration) {
        Employee employee1 = employeeService.findEmployeeById(employeeId1);
        Employee employee2 = employeeService.findEmployeeById(employeeId2);
        if (employee1 == null || employee2 == null) {
            throw new IllegalArgumentException("Employee(s) not found");
        }

        List<LocalDateTime> employee1FreeSlots = getFreeSlots(employee1.getCalender(), meetingDuration);
        List<LocalDateTime> employee2FreeSlots = getFreeSlots(employee2.getCalender(), meetingDuration);

        List<LocalDateTime> freeSlots = new ArrayList<>();
        for (LocalDateTime slot1 : employee1FreeSlots) {
            for (LocalDateTime slot2 : employee2FreeSlots) {
                if (meetingConflictStrategy.isFreeSlot(slot1, slot2, meetingDuration, employee1.getCalender(), employee2.getCalender())) {
                    freeSlots.add(slot1);
                    break;
                }
            }
        }
        return freeSlots;
    }
    private List<LocalDateTime> getFreeSlots(Calender calendar, int meetingDuration) {
        List<LocalDateTime> freeSlots = new ArrayList<>();
        LocalDateTime currentStart = calendar.getStartDateTime();
        LocalDateTime currentEnd = currentStart.plusMinutes(meetingDuration);
        for (Meeting meeting : calendar.getMeetings()) {
            if (currentEnd.isBefore(meeting.getStartDateTime())) {
                freeSlots.add(currentStart); // Slot available before this meeting
            }
            currentStart = meeting.getEndDateTime();
            currentEnd = currentStart.plusMinutes(meetingDuration);
        }

        // Check for free slot after the last meeting
        if (currentEnd.isBefore(calendar.getEndDateTime())) {
            freeSlots.add(currentStart); // Slot available after the last meeting
        }
        return freeSlots;
    }
    @Override
    public List<Employee> findMeetingConflicts(Meeting meeting) {
        List<Employee> conflictingEmployees = new ArrayList<>();
        for (Employee employee : employeeService.getAllEmployees()) {
            if (conflictsWithMeeting(employee.getCalender(), meeting)) {
                conflictingEmployees.add(employee);
            }
        }
        return conflictingEmployees;
    }

    private boolean conflictsWithMeeting(Calender calender, Meeting meeting) {
        for (Meeting existingMeeting : calender.getMeetings()) {
            if (existingMeeting.overlaps(meeting)) {
                return true;
            }
        }
        return false;
    }
}
