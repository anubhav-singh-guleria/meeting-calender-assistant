package com.anubhav.calenderassistant.service.strategy;

import com.anubhav.calenderassistant.model.Calender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service("BasicConflictStrategy")
public class BasicConflictStrategy implements MeetingConflictStrategy{
    @Override
    public boolean isFreeSlot(LocalDateTime slot1, LocalDateTime slot2, int meetingDuration, Calender calendar1, Calender calendar2) {
        return slot1.isBefore(slot2.plusMinutes(meetingDuration)) && slot2.isAfter(slot1);
    }
}
