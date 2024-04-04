package com.anubhav.calenderassistant.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class Calender {
    private long ownerId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private List<Meeting> meetings;

}
