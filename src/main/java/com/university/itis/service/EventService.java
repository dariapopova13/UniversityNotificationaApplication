package com.university.itis.service;

import com.university.itis.dto.EventDto;

import java.util.Date;
import java.util.List;

public interface EventService extends BaseService<EventDto> {

    List<EventDto> getAllByGroupId(Long groupId);

    List<EventDto> getAllByUser(String date);

    List<EventDto> getAllByUser();
}
