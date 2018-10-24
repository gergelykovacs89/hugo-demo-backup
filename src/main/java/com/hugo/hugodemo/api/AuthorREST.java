package com.hugo.hugodemo.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.hugo.hugodemo.model.users.Author;
import com.hugo.hugodemo.model.users.UserProfile;
import com.hugo.hugodemo.repository.AuthorRepository;
import com.hugo.hugodemo.repository.UserProfileRepository;
import com.hugo.hugodemo.util.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorREST {

    final
    AuthorRepository authorRepository;

    final
    UserProfileRepository userProfileRepository;

    @Autowired
    public AuthorREST(AuthorRepository authorRepository, UserProfileRepository userProfileRepository) {
        this.authorRepository = authorRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @CrossOrigin(origins = "http://0.0.0.0:4200")
    @GetMapping("author/{id}")
    @JsonView(View.ExtendedPublic.class)
    public ResponseEntity getAuthor(@PathVariable("id") long id) {
        Author author = authorRepository.getOne(id);
        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("author/new")
    public ResponseEntity addAuthor(@RequestBody Author author,
                                    @RequestParam("userId") long userId) {
        UserProfile user = userProfileRepository.getOne(userId);
        authorRepository.save(new Author(user, author.getAuthorName()));
        return new ResponseEntity(HttpStatus.OK);
    }


}
