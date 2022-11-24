package com.davis.microapi.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.aspectj.weaver.ast.Instanceof;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "goals")
public class Goal {
    @Id @GeneratedValue
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    private String uuid;
    private String name;
    private String verb;
    private String measurement;
    private double quantity;
    private Integer period;
    private String startDate;
    private String endDate;
    private boolean completed;
    private String dateCreated;
    private boolean isTestData;
    private boolean deleted;

    public Goal() {

    }

    public Goal(String uuid, String name, String verb, String measurement, double quanitity, Integer period, String startDate, String endDate, boolean completed, String dateCreated, boolean isTestData, boolean deleted) {
        this.uuid = uuid;
        this.name = name;
        this.verb = verb;
        this.measurement = measurement;
        this.quantity = quanitity;
        this.period = period;
        this.startDate = startDate;
        this.endDate = endDate;
        this.completed = completed;
        this.dateCreated = dateCreated;
        this.isTestData = isTestData;
        this.deleted = deleted;
    }



    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerb() {
        return this.verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getMeasurement() {
        return this.measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quanitity) {
        this.quantity = quanitity;
    }

    public Integer getPeriod() {
        return this.period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isIsTestData() {
        return this.isTestData;
    }

    public boolean getIsTestData() {
        return this.isTestData;
    }

    public void setIsTestData(boolean isTestData) {
        this.isTestData = isTestData;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", getUuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", verb='" + getVerb() + "'" +
            ", measurement='" + getMeasurement() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", period='" + getPeriod() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", completed='" + isCompleted() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", isTestData='" + isIsTestData() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Goal)
        {
            Goal goal = (Goal) o;

            if(
                getUuid().equals(goal.getUuid()) 
                && getName().equals(goal.getName())
                && getVerb().equals(goal.getVerb())
                && getMeasurement().equals(goal.getMeasurement())
                && getQuantity() == goal.getQuantity()
                && getPeriod().equals(goal.getPeriod())
                && getStartDate().equals(goal.getStartDate())
                && getEndDate().equals(goal.getEndDate())
                && isCompleted() == goal.isCompleted()
                && isIsTestData() == goal.isIsTestData()
                && isDeleted() == goal.isDeleted()
            
            ) {
                return true;
            }
            else {
                return false;
            }
        }
        else 
        {
            return false;
        }
        
    }
    

}
