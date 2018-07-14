package com.university.itis.model.builder;

import com.university.itis.model.Token;

import java.util.Date;

public class TokenBuilder {

    private String token;
    private Date endDate;
    private Date startDate;
    private String email;

    public TokenBuilder setToken(String token) {
        this.token = token;
        return this;
    }

    public TokenBuilder setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public TokenBuilder setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public TokenBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public Token createToken() {
        return new Token(token, endDate, startDate, email);
    }
}