package com.university.itis.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.university.itis.dto.EventDto;
import com.university.itis.firebase.FirebaseService;
import com.university.itis.model.Event;
import com.university.itis.model.Role;
import com.university.itis.model.User;
import com.university.itis.repository.EventRepository;
import com.university.itis.repository.GroupRepository;
import com.university.itis.service.EventService;
import com.university.itis.service.UserService;
import com.university.itis.utils.DateUtils;
import com.university.itis.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private DtoUtils dtoUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private DateUtils dateUtils;
    @Autowired
    private FirebaseService firebaseService;

    @Override
    public EventDto get(Long id) {
        Event event = eventRepository.findOne(id);
        return event == null ? null : new EventDto(event);
    }

    @Override
    public EventDto saveOrUdpate(EventDto eventDto) {
        Event event = dtoUtils.toEntity(eventDto);
        event = eventRepository.save(event);
        if (event != null) {
            EventDto dto = new EventDto(event);
            try {
                firebaseService.notifyEvent(dto);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return dto;
        }
        return null;
    }

    @Override
    public List<EventDto> delete(Long id) {
        eventRepository.delete(id);
        return getAllByUser();
    }

    @Override
    public List<EventDto> getAllByGroupId(Long groupId) {
        return eventRepository.findAllByGroupIdOrderByDateAsc(groupId)
                .stream()
                .map(EventDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getAllByUser(String stringDate) {
        User user = userService.getCurrentUser();
        Date date = stringDate == null ? new Date() : dateUtils.toDate(stringDate);
        Pair<Date, Date> dateRange = dateUtils.getDateDayRange(date);
        if (user.getRole() == Role.DEAN) {
            return eventRepository.findAllByDateIsLessThanEqualAndDateIsGreaterThanEqualOrderByDateAsc(
                    dateRange.getSecond(), dateRange.getFirst())
                    .stream()
                    .map(EventDto::new)
                    .collect(Collectors.toList());
        } else {
            return eventRepository.findAllByDateIsLessThanEqualAndDateIsGreaterThanEqualAndGroupInOrderByDateAsc(
                    dateRange.getSecond(), dateRange.getFirst(), groupRepository.findAllByUser(user))
                    .stream()
                    .map(EventDto::new)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<EventDto> getAllByUser() {
        User user = userService.getCurrentUser();
        if (user.getRole() == Role.DEAN) {
            return eventRepository.findAll()
                    .stream()
                    .map(EventDto::new)
                    .collect(Collectors.toList());
        } else {
            return eventRepository.findAllByGroupInOrderByDateAsc(groupRepository.findAllByUser(user))
                    .stream()
                    .map(EventDto::new)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<EventDto> getAllByGroupsId(List<Long> groupsId) {
        return eventRepository.findAllByGroupIdInOrderByDateAsc(groupsId)
                .stream()
                .map(EventDto::new)
                .collect(Collectors.toList());
    }
}
