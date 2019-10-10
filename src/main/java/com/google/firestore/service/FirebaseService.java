package com.google.firestore.service;

import com.google.firestore.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FirebaseService {
    List<User> getUsers() throws Exception;
    String addUser() throws Exception;
}
