package com.google.firestore.controller;

import com.google.firestore.entity.User;
import com.google.firestore.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class FirestoreController {
    @Autowired
    FirebaseService firebaseService;

    @GetMapping("/getUsers")
    public List<User> getAllAccounts() {
        List<User> accountList = null;
        try {
            accountList = firebaseService.getUsers();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountList;
    }
    @GetMapping("/addUser")
    public String addUser() {
        String addUser=null;
        try {
            addUser = firebaseService.addUser();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addUser;
    }
}
