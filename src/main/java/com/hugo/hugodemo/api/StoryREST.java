package com.hugo.hugodemo.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.hugo.hugodemo.model.story.Story;
import com.hugo.hugodemo.model.tree.StoryTree;
import com.hugo.hugodemo.model.users.Author;
import com.hugo.hugodemo.repository.AuthorRepository;
import com.hugo.hugodemo.repository.StoryRepository;
import com.hugo.hugodemo.util.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/story")
public class StoryREST {

    private final
    StoryRepository storyRepository;

    private final
    AuthorRepository authorRepository;

    @Autowired
    public StoryREST(StoryRepository storyRepository, AuthorRepository authorRepository) {
        this.storyRepository = storyRepository;
        this.authorRepository = authorRepository;
    }

    @CrossOrigin(origins = "http://0.0.0.0:4200")
    @GetMapping("{storyId}")
    @JsonView(View.Public.class)
    public ResponseEntity getStoryById(@PathVariable("storyId") long storyId) {
        Story story = storyRepository.getOne(storyId);
        if (story != null) {
            return new ResponseEntity<>(story, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(origins = "http://0.0.0.0:4200")
    @GetMapping("author/{author_id}")
    @JsonView(View.Public.class)
    public ResponseEntity getStoriesByAuthor(@PathVariable("author_id") long authorId) {
        Set<Story> storiesByAuthor = storyRepository.getAllByAuthorId(authorId);
        if (storiesByAuthor != null) {
            return new ResponseEntity<>(storiesByAuthor, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("new")
    public ResponseEntity addStory(@RequestBody Story story,
                                   @RequestParam("authorId") long authorId) {
        Author author = authorRepository.getOne(authorId);
        Story storyToDB = new Story(story.getStoryTitle(), author);
        StoryTree storyTree = new StoryTree(storyToDB);
        storyRepository.save(storyToDB);
        return new ResponseEntity(HttpStatus.OK);
    }

}
