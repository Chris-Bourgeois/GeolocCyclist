/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stema.beans;

import com.stema.converter.IdSQLDBConverter;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.bson.types.ObjectId;

/**
 *
 * @author bourg
 */
@Entity
public class Activity implements Serializable {
    
    @Id
    @Convert(converter = IdSQLDBConverter.class)
    private ObjectId _id;
    
    private String cyclistId;
    private Date startTime;
    private Date stopTime;

    public Activity() {
    }

    public Activity(ObjectId _id, Date startTime, Date stopTime) {
        this._id = _id;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getCyclistId() {
        return cyclistId;
    }

    public void setCyclistId(String cyclistId) {
        this.cyclistId = cyclistId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    @Override
    public String toString() {
        return "Activity{" + "_id=" + _id + ", startTime=" + startTime + ", stopTime=" + stopTime + '}';
    }    

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }
}
