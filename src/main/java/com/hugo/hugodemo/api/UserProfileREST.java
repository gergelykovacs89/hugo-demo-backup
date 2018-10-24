package com.hugo.hugodemo.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.hugo.hugodemo.model.users.UserProfile;
import com.hugo.hugodemo.repository.UserProfileRepository;
import com.hugo.hugodemo.util.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserProfileREST {

    final
    UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileREST(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @CrossOrigin(origins = "http://0.0.0.0:4200")
    @GetMapping("profile/{id}")
    @JsonView(View.Public.class)
    public ResponseEntity getUserProfile(@PathVariable("id") long id) {
        UserProfile userProfile = userProfileRepository.getOne(id);
        if (userProfile != null) {
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("profile/new")
    public ResponseEntity addUserProfile(@RequestBody UserProfile userProfile) {
        userProfileRepository.save(new UserProfile(userProfile.getEmail(), userProfile.getPassword(), userProfile.getFullName()));
        return new ResponseEntity(HttpStatus.OK);
    }

}
