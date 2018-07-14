package com.university.itis.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.itis.model.common.AbstractEntity;


@JsonIgnoreProperties(ignoreUnknown = true)
public  class AbstractDto {

    private Long id;

    public AbstractDto(AbstractEntity entity) {
        this.id = entity.getId();
    }

    public AbstractDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
