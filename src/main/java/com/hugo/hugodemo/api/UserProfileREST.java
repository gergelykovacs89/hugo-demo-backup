package com.hugo.hugodemo.api;

import com.hugo.hugodemo.model.users.Author;
import com.hugo.hugodemo.model.users.UserProfile;
import com.hugo.hugodemo.repository.AuthorRepository;
import com.hugo.hugodemo.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/profile")
public class UserProfileREST {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("new")
    public ResponseEntity addUserProfile(@RequestBody UserProfile userProfile) {
        if (userProfileRepository.getByEmail(userProfile.getEmail()) == null) {
            userProfileRepository.save(new UserProfile(userProfile.getEmail(), userProfile.getFullName()));
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            System.out.println("User already exists!");
            UserProfile user = userProfileRepository.getByEmail(userProfile.getEmail());
            Author[] authors = authorRepository.getAuthorsByUserProfileUserId(user.getUserId());
            return new ResponseEntity<>(authors, HttpStatus.OK);
        }
    }

}
