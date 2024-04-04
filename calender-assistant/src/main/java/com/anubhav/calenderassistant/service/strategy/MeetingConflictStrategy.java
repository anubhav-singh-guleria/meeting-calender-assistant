package com.anubhav.calenderassistant.service.strategy;

import com.anubhav.calenderassistant.model.Calender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public interface MeetingConflictStrategy {
    boolean isFreeSlot(LocalDateTime slot1, LocalDateTime slot2, int meetingDuration, Calender calendar1, Calender calendar2);
}
