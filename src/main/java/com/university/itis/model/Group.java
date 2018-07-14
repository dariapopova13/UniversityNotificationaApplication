package com.university.itis.model;


import com.university.itis.model.common.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.Set;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "group")
//@Indexed
public class Group extends AbstractEntity {

    @Column(name = "name")
    private String name;
    @ManyToOne
    private User user;
    @Column(name = "info")
    @Lob
    private String info;
    @ManyToOne
    private Group parent;
    @ManyToOne
    private User admin;
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<Group> children;

    public Set<Group> getChildren() {
        return children;
    }

    public void setChildren(Set<Group> children) {
        this.children = children;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getParent() {
        return parent;
    }

    public void setParent(Group parent) {
        this.parent = parent;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
