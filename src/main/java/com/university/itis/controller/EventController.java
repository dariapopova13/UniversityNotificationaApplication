package com.university.itis.controller;

import com.university.itis.dto.EventDto;
import com.university.itis.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/event/")
public class EventController implements BaseController<EventDto> {

    @Autowired
    private EventService eventService;

    @Override
    @PostMapping(value = "save")
    public EventDto saveOrUpdate(@RequestBody EventDto eventDto) {
        return eventService.saveOrUdpate(eventDto);
    }

    @Override
    @DeleteMapping(value = "{id}")
    public List<EventDto> delete(@PathVariable Long id) {
        return eventService.delete(id);
    }

    @Override
    @GetMapping(value = "{id}")
    public EventDto get(@PathVariable Long id) {
        return eventService.get(id);
    }

    @GetMapping(value = "group/{groupId}")
    public List<EventDto> getAllByGroupId(@PathVariable Long groupId) {
        return eventService.getAllByGroupId(groupId);
    }

    @GetMapping(value = "user")
    public List<EventDto> getAllByUser(@RequestParam(name = "date", required = false) String date) {
        return eventService.getAllByUser(date);
    }

    @GetMapping(value = "user/all")
    public List<EventDto> getAllByUser() {
        return eventService.getAllByUser();
    }
}
