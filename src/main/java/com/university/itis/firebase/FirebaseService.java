package com.university.itis.firebase;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.university.itis.dto.EventDto;
import com.university.itis.dto.GroupDto;
import com.university.itis.model.Event;
import com.university.itis.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    @Autowired
    private DateUtils dateUtils;
    @Autowired
    private ObjectMapper objectMapper;


    public void notifyEvent(EventDto event) throws JsonProcessingException {

        Message.Builder builder = Message.builder()
                .putData("title", event.getTitle())
                .putData("date", event.getDate().toString())
                .putData("group", event.getGroup().getName())
                .putData("event", event.getText());


        builder.setTopic(event.getGroup().getId().toString());
        send(builder.build());
        notifyTreeStructureGroup(event.getGroup(), builder);
    }

    @Async
    public void send(Message message) {
        FirebaseMessaging.getInstance().sendAsync(message);
    }

    @Async
    public void notifyTreeStructureGroup(GroupDto group, Message.Builder builder) {
        if (group.getChildren() != null && group.getChildren().size() > 0) {
            for (GroupDto child : group.getChildren()) {
                notifyTreeStructureGroup(child, builder);
            }
        }
        builder.setTopic(group.getId().toString());
        send(builder.build());
    }


    @Async
    public void notifyEvent(Event event) throws JsonProcessingException {
        if (event == null) return;
        notifyEvent(new EventDto(event));
    }
}
