package com.company.flightsystem.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "FLIGHTSYSTEM_FLIGHT")
@Entity(name = "flightsystem_Flight")
public class Flight extends StandardEntity {
    private static final long serialVersionUID = -5984377066785778806L;

    @Column(name = "DEPARTURE")
    private String departure;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_")
    private Date date;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "PRICE")
    private String price;

    @Column(name = "EMPTY_SEAT")
    private String emptySeat;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmptySeat() {
        return emptySeat;
    }

    public void setEmptySeat(String emptySeat) {
        this.emptySeat = emptySeat;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

}