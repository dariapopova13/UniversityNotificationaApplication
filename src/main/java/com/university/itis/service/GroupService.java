package com.university.itis.service;

import com.university.itis.dto.GroupDto;
import com.university.itis.dto.SimpleGroupDto;
//import com.university.itis.dto.SimpleGroupDto;

import java.util.List;

public interface GroupService extends BaseService<GroupDto> {

    List<GroupDto> getChildren(Long id);

    List<GroupDto> getAllByUser();

    List<GroupDto> getAll();

}
