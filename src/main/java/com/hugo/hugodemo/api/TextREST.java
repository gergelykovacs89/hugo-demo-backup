package com.hugo.hugodemo.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.hugo.hugodemo.api.JSONModels.ChildText;
import com.hugo.hugodemo.api.JSONModels.RootText;
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
@RequestMapping("/text")
public class TextREST {

    private final
    TextNodeRepository textNodeRepository;

    private final
    AuthorRepository authorRepository;

    private final
    StoryRepository storyRepository;

    @Autowired
    public TextREST(TextNodeRepository textNodeRepository, AuthorRepository authorRepository, StoryRepository storyRepository) {
        this.textNodeRepository = textNodeRepository;
        this.authorRepository = authorRepository;
        this.storyRepository = storyRepository;
    }

    @CrossOrigin(origins = "http://0.0.0.0:4200")
    @GetMapping("{textNodeId}")
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
    @GetMapping("author/{author_id}")
    @JsonView(View.Public.class)
    public ResponseEntity getTextsByAuthor(@PathVariable("author_id") long authorId) {
        Author author = authorRepository.getOne(authorId);
        if (author != null) {
            return new ResponseEntity<>(author.getTextList(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("story/new-child")
    public ResponseEntity addChildText(@RequestBody ChildText childText) {
        Author author = authorRepository.getOne(childText.getAuthorId());
        TextNode parentNode = textNodeRepository.getOne(childText.getParentNodeId());
        Text childTextToDB = new Text(childText.getText(), author);
        TextNode childNode = new TextNode(childTextToDB);
        parentNode.addChild(childNode);
        textNodeRepository.save(parentNode);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("story/new-root")
    public ResponseEntity addRootText(@RequestBody RootText rootText) {
        Author author = authorRepository.getOne(rootText.getAuthorId());
        Story story = storyRepository.getOne(rootText.getStoryId());
        Text rootTextToDB = new Text(rootText.getText(), author);
        TextNode rootNode = new TextNode(rootTextToDB);
        story.setRootTextNode(rootNode);
        textNodeRepository.save(rootNode);
        return new ResponseEntity(HttpStatus.OK);
    }
}
