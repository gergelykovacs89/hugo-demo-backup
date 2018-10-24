package com.hugo.hugodemo.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.hugo.hugodemo.model.story.Story;
import com.hugo.hugodemo.model.story.textunit.Text;
import com.hugo.hugodemo.model.tree.TextNode;
import com.hugo.hugodemo.model.users.Author;
import com.hugo.hugodemo.repository.AuthorRepository;
import com.hugo.hugodemo.repository.StoryRepository;
import com.hugo.hugodemo.repository.TextNodeRepository;
import com.hugo.hugodemo.util.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TextREST {

    final
    TextNodeRepository textNodeRepository;

    final
    AuthorRepository authorRepository;

    final
    StoryRepository storyRepository;

    @Autowired
    public TextREST(TextNodeRepository textNodeRepository, AuthorRepository authorRepository, StoryRepository storyRepository) {
        this.textNodeRepository = textNodeRepository;
        this.authorRepository = authorRepository;
        this.storyRepository = storyRepository;
    }

    @CrossOrigin(origins = "http://0.0.0.0:4200")
    @GetMapping("story/text/{textNodeId}")
    @JsonView(View.Public.class)
    public ResponseEntity getTextByNodeId(@PathVariable("textNodeId") long textNodeId) {
        TextNode textNode = textNodeRepository.getOne(textNodeId);
        if (textNode != null) {
            Text text = textNode.getText();
            return new ResponseEntity<>(text, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://0.0.0.0:4200")
    @GetMapping("story/text/{author_id}")
    @JsonView(View.Public.class)
    public ResponseEntity getTextsByAuthor(@PathVariable("author_id") long authorId) {
        Author author = authorRepository.getOne(authorId);
        if (author != null) {
            return new ResponseEntity<>(author.getTextList(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("story/text/new-child")
    public ResponseEntity addChildText(@RequestBody Text text,
                                       @RequestParam("parentNodeId") long parentNodeId,
                                       @RequestParam("authorId") long authorId) {
        Author author = authorRepository.getOne(authorId);
        TextNode parentNode = textNodeRepository.getOne(parentNodeId);
        Text childText = new Text(text.getTextett(), author);
        TextNode childNode = new TextNode(childText);
        parentNode.addChild(childNode);
        textNodeRepository.save(parentNode);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("story/text/new-root")
    public ResponseEntity addRootText(@RequestBody Text text,
                                      @RequestParam("storyId") long storyId,
                                      @RequestParam("authorId") long authorId) {
        Author author = authorRepository.getOne(authorId);
        Story story = storyRepository.getOne(storyId);
        Text rootText = new Text(text.getTextett(), author);
        TextNode rootNode = new TextNode(rootText);
        story.setRootTextNode(rootNode);
        textNodeRepository.save(rootNode);
        return new ResponseEntity(HttpStatus.OK);
    }


}
