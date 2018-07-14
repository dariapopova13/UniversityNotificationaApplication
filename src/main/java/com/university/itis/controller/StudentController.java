package com.university.itis.controller;

import com.university.itis.dto.GroupDto;
import com.university.itis.service.DocumentService;
import com.university.itis.service.EventService;
import com.university.itis.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/student/api/")
public class StudentController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private EventService eventService;
    @Autowired
    private DocumentService documentService;


    @GetMapping(value = "group/{id}")
    public GroupDto get(@PathVariable Long id) {
        return groupService.get(id);
    }

    @GetMapping(value = {"group/", "group"})
    public List<GroupDto> getAll() {
        return groupService.getAll();
    }


}
