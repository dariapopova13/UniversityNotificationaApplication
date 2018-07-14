package com.university.itis.model;

import com.university.itis.model.common.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "event")
public class Event extends AbstractEntity {

    @Column(name = "event_text")
    @Lob
//    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES,
//            analyzer = @Analyzer(definition = "customanalyzer"))
    private String text;
    @Column(name = "event_date")
    private Date date;
    @Lob
    @Column(name = "event_title")
//    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES,
//            analyzer = @Analyzer(definition = "customanalyzer"))
    private String title;
    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private Set<Document> documents= new HashSet<>();
    @ManyToOne
    @IndexedEmbedded
    private Group group;

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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


}
