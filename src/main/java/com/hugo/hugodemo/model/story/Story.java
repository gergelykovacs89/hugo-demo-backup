package com.hugo.hugodemo.model.story;

import com.fasterxml.jackson.annotation.JsonView;
import com.hugo.hugodemo.model.tree.StoryTree;
import com.hugo.hugodemo.model.tree.TextNode;
import com.hugo.hugodemo.model.users.Author;
import com.hugo.hugodemo.util.View;

import javax.persistence.*;

@Entity
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    private Long storyId;

    @Column(nullable = false)
    @JsonView(View.Public.class)
    private String storyTitle;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @OneToOne
    @JoinColumn(name = "story_tree_id")
    @MapsId
    @JsonView(View.Public.class)
    private StoryTree storyTree;


    public Story(String storyTitle, Author author) {
        this.storyTitle = storyTitle;
        this.author = author;
        author.addStory(this);
    }

    public Story() {
    }

    public Long getStoryId() {
        return storyId;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public Author getAuthor() {
        return author;
    }

    private StoryTree getStoryTree() {
        return storyTree;
    }

    public void setStoryTree(StoryTree storyTree) {
        this.storyTree = storyTree;
    }

    public void setRootTextNode(TextNode textNode) {
        this.getStoryTree().setRootTextNode(textNode);
    }
}
