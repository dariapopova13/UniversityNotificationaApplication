package com.university.itis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.itis.model.Group;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDto extends SimpleGroupDto {

    private SimpleGroupDto parent;
    private List<GroupDto> children;

    public GroupDto(Group group) {
        super(group);
        if (group.getParent() != null)
            this.parent = new SimpleGroupDto(group.getParent());
        if (group.getChildren() != null)
            this.children = group.getChildren()
                    .stream()
                    .map(GroupDto::new)
                    .collect(Collectors.toList());

    }

    public GroupDto() {
    }

    public List<GroupDto> getChildren() {
        return children;
    }

    public SimpleGroupDto getParent() {
        return parent;
    }

    public void setParent(SimpleGroupDto parent) {
        this.parent = parent;
    }

    public void setChildren(List<GroupDto> children) {
        this.children = children;
    }
}
