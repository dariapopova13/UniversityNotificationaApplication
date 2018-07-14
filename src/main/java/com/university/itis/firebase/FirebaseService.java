package com.university.itis.firebase;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
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

//    @Async
//    public void notifyNews(NewsDto news) throws JsonProcessingException {
//        String topik = null;
//
//        String newsJson = objectMapper.writeValueAsString(news);
//
//        Message.Builder builder = Message.builder()
//                .putData("title", news.getTitle())
//                .putData("news", newsJson);
//
//        if (news.getYear() != null) {
//            topik = "Dean" + news.getYear();
//        } else if (news.getCourse() != null) {
//            topik = "Group" + news.getCourse().getId();
//        }
//
//        builder.setTopic(topik);
//
//        FirebaseMessaging.getInstance().sendAsync(builder.build());
//    }
//
//    @Async
//    public void notifyNews(News news) throws JsonProcessingException {
//        if (news == null) return;
//        notifyNews(new NewsDto(news));
//
//    }
}
