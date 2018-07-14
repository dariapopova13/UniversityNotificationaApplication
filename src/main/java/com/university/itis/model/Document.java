package com.university.itis.model;

import com.university.itis.model.common.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "docs")
public class Document extends AbstractEntity {

    @ManyToOne
    private Event event;
    @Lob
    @Column(name = "doc_file")
    private byte[] file;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
