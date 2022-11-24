package com.davis.microapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "progress")
public class Progress {

    @Id @GeneratedValue
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    private String goalUuid;
    private String uuid;
    private double units;
    private boolean testData;
    private boolean deleted;


    public Progress() {
    }


    public Progress(String goalUuid, String uuid, double units, boolean testData, boolean deleted) {
        this.goalUuid = goalUuid;
        this.uuid = uuid;
        this.units = units;
        this.testData = testData;
        this.deleted = deleted;
    }


    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoalUuid() {
        return this.goalUuid;
    }

    public void setGoalUuid(String goalUuid) {
        this.goalUuid = goalUuid;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public double getUnits() {
        return this.units;
    }

    public void setUnits(double units) {
        this.units = units;
    }

    public boolean isTestData() {
        return this.testData;
    }

    public boolean getTestData() {
        return this.testData;
    }

    public void setTestData(boolean testData) {
        this.testData = testData;
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
    public boolean equals(Object o) {
        
        if(o instanceof Progress)
        {
            
            Progress event = (Progress) o;

            if(
                getUuid().equals(event.getUuid()) 
                && getUnits() == event.getUnits()
                && isTestData() == event.isTestData()
                && isDeleted() == event.isDeleted()
            
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


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", goalUuid='" + getGoalUuid() + "'" +
            ", uuid='" + getUuid() + "'" +
            ", units='" + getUnits() + "'" +
            ", testData='" + isTestData() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }


}
