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
@RequestMapping("/author")
public class AuthorREST {

    private final
    AuthorRepository authorRepository;

    private final
    UserProfileRepository userProfileRepository;

    @Autowired
    public AuthorREST(AuthorRepository authorRepository, UserProfileRepository userProfileRepository) {
        this.authorRepository = authorRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("{username}")
    @JsonView(View.Public.class)
    public ResponseEntity getAuthor(@PathVariable("username") String username) {
        Author author = authorRepository.getAuthorByName(username);
        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("new")
    public ResponseEntity addAuthor(@RequestBody Author author,
                                    @RequestParam("userEmail") String userMail) {
        UserProfile user = userProfileRepository.getByEmail(userMail);
        authorRepository.save(new Author(user, author.getName(), author.getImgPath(), author.getDescription()));
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("delete")
    public ResponseEntity addAuthor(@RequestParam("authorToDelete") String authorName) {
        authorRepository.deleteAuthorByName(authorName);
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("update")
    public ResponseEntity updateAuthor(@RequestBody Author author,
                                       @RequestParam("userEmail") String userMail) {
        Author authorToUpdate = authorRepository.getAuthorByName(author.getName());
        System.out.println(author.getName());
        if (authorToUpdate != null) {
            authorToUpdate.setImgPath(author.getImgPath());
            authorToUpdate.setDescription(author.getDescription());
        }
        authorRepository.save(authorToUpdate);
        return new ResponseEntity(HttpStatus.OK);
    }


}
