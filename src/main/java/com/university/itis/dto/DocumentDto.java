package com.university.itis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.itis.dto.common.AbstractDto;
import com.university.itis.model.Document;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentDto extends AbstractDto {

    private Long eventId;
    private byte[] file;

    public DocumentDto() {

    }

    public DocumentDto(Document document) {
        super(document);
        if (document.getEvent() != null)
            this.eventId = document.getEvent().getId();
        this.file = document.getFile();
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
