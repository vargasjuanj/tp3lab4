package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import java.util.Date;

public abstract class Base {
    @Id
    protected String id; //Si no le doy un valor mongo crea uno por defecto
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
   protected Date date=new Date();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
