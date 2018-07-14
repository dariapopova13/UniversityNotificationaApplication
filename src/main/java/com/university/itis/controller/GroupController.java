package com.university.itis.controller;

import com.university.itis.dto.GroupDto;
import com.university.itis.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/group/")
public class GroupController implements BaseController<GroupDto> {

    @Autowired
    private GroupService groupService;

    @PostMapping(value = "save")
    public GroupDto saveOrUpdate(@RequestBody GroupDto group) {
        return groupService.saveOrUdpate(group);
    }

    @DeleteMapping(value = "{id}")
    public List<GroupDto> delete(@PathVariable Long id) {
        return groupService.delete(id);
    }

    @GetMapping(value = "{id}")
    public GroupDto get(@PathVariable Long id) {
        return groupService.get(id);
    }

    @GetMapping(value = "{id}/children")
    public List<GroupDto> getChildren(@PathVariable Long id) {
        return groupService.getChildren(id);
    }

    @GetMapping(value = "/user")
    public List<GroupDto> getAllByUser() {
        return groupService.getAllByUser();
    }

    @GetMapping(value = {"/", ""})
    public List<GroupDto> getAll() {
        return groupService.getAll();
    }
}
