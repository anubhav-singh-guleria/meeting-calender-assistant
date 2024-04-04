package com.anubhav.calenderassistant.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Meeting {
    private long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public boolean overlaps(Meeting meeting) {
        return this.getStartDateTime().isBefore(meeting.getEndDateTime()) &&
                meeting.getStartDateTime().isBefore(this.getEndDateTime());
    }
}
