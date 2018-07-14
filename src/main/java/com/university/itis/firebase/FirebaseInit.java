package com.university.itis.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class FirebaseInit {


//    @PostConstruct
//    public void firebase() throws IOException {
//       String credentialFile = "/itisapp-1522147861835-firebase-adminsdk-boglk-f45cb10126.json";
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(
//                        this.getClass().getResourceAsStream(credentialFile)))
//                .build();
//
//        try {
//            FirebaseApp.initializeApp(options);
//        } catch (Exception e) {
//        }
//    }
}
