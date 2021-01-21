package org.acme.optaplanner.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ExamensTest {
    Examens exam = new Examens(2, "Informatique", "Anglais", "2021InformatiqueAnglais");
    Room room = new Room("F21", 300);
    Date date = new Date(15/02/2000);
    LocalTime localTime1 = LocalTime.of(15, 30, 0, 0);
    LocalTime localTime2 = LocalTime.of(16, 30, 0, 0);
    Timeslot slot1 = new Timeslot(date, localTime1, localTime2);

    @Test
    void TestExam() {
        long id = exam.getId();
        assertEquals(0, id);
        exam.setTimeslot(slot1);
        Timeslot slot2 = exam.getTimeslot();
        assertEquals(slot1.getStartTime(), slot2.getStartTime());
        assertEquals(slot1.getEndTime(), slot2.getEndTime());
        assertEquals(exam.getStudentGroup(), "Informatique2");

    }
    @Test
    void TestRoom(){
        Room r = exam.getRoom();
        assertEquals(r, null);
        exam.setRoom(room);
        r = new Room("F21", 300);
        assertEquals(r.getName(), room.getName());
        assertEquals(r.getSits(), room.getSits());


    }
}