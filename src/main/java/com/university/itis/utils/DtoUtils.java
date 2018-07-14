package com.university.itis.utils;

import com.university.itis.dto.*;
import com.university.itis.model.*;
import com.university.itis.repository.DocumentRepository;
import com.university.itis.repository.EventRepository;
import com.university.itis.repository.GroupRepository;
import com.university.itis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DtoUtils {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DocumentRepository documentRepository;

    public Group toEntity(GroupDto dto) {
        Group group;
        if (dto.getId() != null) {
            group = groupRepository.findOne(dto.getId());
        } else group = new Group();

        group.setInfo(dto.getInfo());
        group.setName(dto.getName());

        if (dto.getUser() != null)
            group.setUser(toEntity(dto.getUser()));
        if (dto.getParent() != null) {
            Group parent = new Group();
            parent.setId(dto.getParent().getId());
            group.setParent(parent);
        }
        if (dto.getAdmin() != null) {
            group.setAdmin(userRepository.findOne(dto.getAdmin().getId()));
        }
        return group;
    }

    public User toEntity(UserDto dto) {
        User user;
        if (dto.getId() != null) {
            user = userRepository.findOne(dto.getId());
        } else user = new User();

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        if (dto.getPassword() != null)
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.getRole(dto.getRole()));
        return user;
    }

    public Event toEntity(EventDto dto) {
        Event event;
        if (dto.getId() != null) {
            event = eventRepository.findOne(dto.getId());
        } else event = new Event();
        event.setDate(dto.getDate());
        event.setTitle(dto.getTitle());
        event.setText(dto.getText());

        if (dto.getGroup() != null) {
            event.setGroup(toEntity((GroupDto) dto.getGroup()));
        }
        if (dto.getDocuments() != null) {
            event.setDocuments(
                    dto.getDocuments().stream()
                            .map(this::toEntity)
                            .collect(Collectors.toSet())
            );
        }
        return event;
    }

    public Document toEntity(DocumentDto dto) {
        Document document;
        if (dto.getId() != null) {
            document = documentRepository.findOne(dto.getId());
        } else document = new Document();
        document.setEvent(eventRepository.findOne(dto.getEventId()));
        document.setFile(dto.getFile());
        return document;
    }

}
