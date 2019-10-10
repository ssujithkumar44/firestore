package com.google.firestore.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firestore.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FirebaseServiceImpl implements FirebaseService {
    @Override
    public List<User> getUsers() throws Exception{
        Firestore db = getFirestore();
        List<User> users = getUsers(db);
        return users;
    }

    private Firestore getFirestore() {
        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId("gcp-kubernetes-springboot")
                        .build();
        return firestoreOptions.getService();
    }

    private List<User> getUsers(Firestore db) throws InterruptedException, java.util.concurrent.ExecutionException {
        // asynchronously retrieve all users
        ApiFuture<QuerySnapshot> query = db.collection("users").get();
// ...
// query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        List<User> users =new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            User user =new User();
            user.setFirstName(document.getString("first"));
            user.setLastName(document.getString("last"));
            users.add(user);
        }
        return users;
    }
    @Override
    public String addUser() throws Exception {
        Firestore db = getFirestore();
        DocumentReference docRef = db.collection("users").document("aturing");
// Add document data with an additional field ("middle")
        Map<String, Object> data = new HashMap<>();
        data.put("first", "Niran");
        data.put("middle", "");
        data.put("last", "Vijayakumar");
        data.put("born", 1992);

        ApiFuture<WriteResult> result = docRef.set(data);
        return "Update time : " + result.get().getUpdateTime();
    }
}
