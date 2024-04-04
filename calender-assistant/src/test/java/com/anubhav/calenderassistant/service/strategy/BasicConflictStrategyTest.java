package com.anubhav.calenderassistant.service.strategy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.anubhav.calenderassistant.model.Calender;
import com.anubhav.calenderassistant.model.Meeting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {BasicConflictStrategy.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class BasicConflictStrategyTest {
    @Autowired
    private BasicConflictStrategy basicConflictStrategy;

    /**
     * Method under test: {@link BasicConflictStrategy#isFreeSlot(LocalDateTime, LocalDateTime, int, Calender, Calender)}
     */
    @Test
    public void testIsFreeSlot() {
        LocalDateTime slot1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime slot2 = LocalDate.of(1970, 1, 1).atStartOfDay();

        Calender calendar1 = new Calender();
        calendar1.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calendar1.setMeetings(new ArrayList<>());
        calendar1.setOwnerId(1L);
        calendar1.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Calender calendar2 = new Calender();
        calendar2.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calendar2.setMeetings(new ArrayList<>());
        calendar2.setOwnerId(1L);
        calendar2.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assertFalse(basicConflictStrategy.isFreeSlot(slot1, slot2, 1, calendar1, calendar2));
    }

    /**
     * Method under test: {@link BasicConflictStrategy#isFreeSlot(LocalDateTime, LocalDateTime, int, Calender, Calender)}
     */
    @Test
    public void testIsFreeSlot2() {
        LocalDateTime slot1 = LocalDate.now().atStartOfDay();
        LocalDateTime slot2 = LocalDate.of(1970, 1, 1).atStartOfDay();

        Calender calendar1 = new Calender();
        calendar1.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calendar1.setMeetings(new ArrayList<>());
        calendar1.setOwnerId(1L);
        calendar1.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Calender calendar2 = new Calender();
        calendar2.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calendar2.setMeetings(new ArrayList<>());
        calendar2.setOwnerId(1L);
        calendar2.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assertFalse(basicConflictStrategy.isFreeSlot(slot1, slot2, 1, calendar1, calendar2));
    }

    /**
     * Method under test: {@link BasicConflictStrategy#isFreeSlot(LocalDateTime, LocalDateTime, int, Calender, Calender)}
     */
    @Test
    public void testIsFreeSlot3() {
        LocalDateTime slot1 = LocalDate.ofYearDay(1, 1).atStartOfDay();
        LocalDateTime slot2 = LocalDate.of(1970, 1, 1).atStartOfDay();

        Calender calendar1 = new Calender();
        calendar1.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calendar1.setMeetings(new ArrayList<>());
        calendar1.setOwnerId(1L);
        calendar1.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Calender calendar2 = new Calender();
        calendar2.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calendar2.setMeetings(new ArrayList<>());
        calendar2.setOwnerId(1L);
        calendar2.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assertTrue(basicConflictStrategy.isFreeSlot(slot1, slot2, 1, calendar1, calendar2));
    }

    /**
     * Method under test: {@link BasicConflictStrategy#isFreeSlot(LocalDateTime, LocalDateTime, int, Calender, Calender)}
     */
    @Test
    public void testIsFreeSlot4() {
        LocalDateTime slot1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime slot2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Calender calendar1 = mock(Calender.class);
        doNothing().when(calendar1).setEndDateTime(Mockito.<LocalDateTime>any());
        doNothing().when(calendar1).setMeetings(Mockito.<List<Meeting>>any());
        doNothing().when(calendar1).setOwnerId(anyLong());
        doNothing().when(calendar1).setStartDateTime(Mockito.<LocalDateTime>any());
        calendar1.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calendar1.setMeetings(new ArrayList<>());
        calendar1.setOwnerId(1L);
        calendar1.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Calender calendar2 = new Calender();
        calendar2.setEndDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        calendar2.setMeetings(new ArrayList<>());
        calendar2.setOwnerId(1L);
        calendar2.setStartDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assertFalse(basicConflictStrategy.isFreeSlot(slot1, slot2, 1, calendar1, calendar2));
        verify(calendar1).setEndDateTime(Mockito.<LocalDateTime>any());
        verify(calendar1).setMeetings(Mockito.<List<Meeting>>any());
        verify(calendar1).setOwnerId(anyLong());
        verify(calendar1).setStartDateTime(Mockito.<LocalDateTime>any());
    }
}

