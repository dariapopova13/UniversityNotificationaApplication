package com.university.itis.service.impl;

import com.university.itis.dto.GroupDto;
import com.university.itis.model.Group;
import com.university.itis.model.Role;
import com.university.itis.model.User;
import com.university.itis.repository.GroupRepository;
import com.university.itis.service.GroupService;
import com.university.itis.service.UserService;
import com.university.itis.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private DtoUtils dtoUtils;
    @Autowired
    private UserService userService;

    @Override
    public GroupDto get(Long id) {
        Group group = groupRepository.findOne(id);
        return group == null ? null : new GroupDto(group);
    }

    @Override
    public GroupDto saveOrUdpate(GroupDto groupDto) {
        Group group = dtoUtils.toEntity((GroupDto) groupDto);
//        group.setUser(userService.getCurrentUser());
        User user
        group.setUser();
        group = groupRepository.save(group);
        return group == null ? null : new GroupDto(group);
    }

    @Override
    public List<GroupDto> delete(Long id) {
//        deleteReferences(id);
        groupRepository.delete(id);
        return getAllByUser();
    }

    private void deleteReferences(Long id){
        List<Group> children = groupRepository.findAllByParentId(id);
        for (Group child : children) {
            child.setParent(null);
        }
        groupRepository.save(children);
    }

    @Override
    public List<GroupDto> getChildren(Long id) {
        return groupRepository.findAllByParentId(id).stream()
                .map(GroupDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupDto> getAllByUser() {
        User current = userService.getCurrentUser();
        if (current.getRole() == Role.DEAN) {
            return groupRepository.findAll()
                    .stream()
                    .map(GroupDto::new)
                    .collect(Collectors.toList());
        } else {
            return groupRepository.findAllByUserIdOrAdminId(current.getId(), current.getId())
                    .stream()
                    .map(GroupDto::new)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<GroupDto> getAll() {
        return groupRepository.findAll()
                .stream()
                .map(GroupDto::new)
                .collect(Collectors.toList());
    }
}
