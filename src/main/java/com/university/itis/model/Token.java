package com.university.itis.model;


import com.university.itis.model.common.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created on 24.05.17.
 */
@DynamicInsert
@DynamicUpdate
@Table(name = "token")
@Entity
public class Token extends AbstractEntity {

    private String token;
    private Date endDate;
    private Date startDate;
    private String email;

    public Token() {
    }

    public Token(String token, Date endDate, Date startDate, String email) {
        this.token = token;
        this.endDate = endDate;
        this.startDate = startDate;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
