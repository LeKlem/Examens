package org.acme.optaplanner.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;

public class Timeslot {

    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Timeslot() {
    }

    public Timeslot(Date date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date date() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return date + " " + startTime;
    }

}
