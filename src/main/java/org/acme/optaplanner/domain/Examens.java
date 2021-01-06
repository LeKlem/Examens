package org.acme.optaplanner.domain;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Examens {

    @PlanningId
    private long id;
    private int annee;
    private String filiere;
    private String matiere;
    private String name;
    private String teacher;
    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private Timeslot timeslot;
    @PlanningVariable(valueRangeProviderRefs = "roomRange")
    private Room room;

    public Examens() {
    }



    public Examens(int annee, String filiere, String matiere, String name) {
        this.annee = annee;
        this.filiere = filiere;
        this.matiere = matiere;
        this.name = name;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Room getRoom() { return room;}
    public long getId() { return id;}
    public String getTeacher() { return teacher;}
    public String getStudentGroup(){
        return filiere + annee;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}