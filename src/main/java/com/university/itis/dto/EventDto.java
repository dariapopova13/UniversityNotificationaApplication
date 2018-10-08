package com.university.itis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.itis.dto.common.AbstractDto;
import com.university.itis.model.Event;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto extends AbstractDto {

    private String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private String title;
    private GroupDto group;
    private List<DocumentDto> documents;

    public EventDto() {
    }

    public EventDto(Event event) {
        super(event);
        this.text = event.getText();
        this.date = event.getDate();
        this.title = event.getTitle();
        if (event.getGroup() != null) {
            this.group = new GroupDto(event.getGroup());
        }
        this.documents = event.getDocuments().stream()
                .map(DocumentDto::new).collect(Collectors.toList());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }

    public List<DocumentDto> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDto> documents) {
        this.documents = documents;
    }
}
