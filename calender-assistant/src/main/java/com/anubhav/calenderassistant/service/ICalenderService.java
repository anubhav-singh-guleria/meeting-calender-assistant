package com.anubhav.calenderassistant.service;

import com.anubhav.calenderassistant.model.Employee;
import com.anubhav.calenderassistant.model.Meeting;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ICalenderService {
    List<LocalDateTime> findFreeSlots(long employeeId1, long employeeId2, int meetingDuration);
    List<Employee> findMeetingConflicts(Meeting meeting);



}
