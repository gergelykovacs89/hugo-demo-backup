package com.hugo.hugodemo.model.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.hugo.hugodemo.model.story.Story;
import com.hugo.hugodemo.util.View;

import javax.persistence.*;

@Entity
public class StoryTree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    private Long storyTreeId;

    @OneToOne(mappedBy = "storyTree")
    @JsonIgnore
    private Story story;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "root_node_id")
    @JsonView(View.Public.class)
    private TextNode rootTextNode;

    public StoryTree() {
    }

    public StoryTree(Story story) {
        this.story = story;
        story.setStoryTree(this);
    }

    public Long getStoryTreeId() {
        return storyTreeId;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public TextNode getRootTextNode() {
        return rootTextNode;
    }

    public void setRootTextNode(TextNode rootTextNode) {
        this.rootTextNode = rootTextNode;
    }
}
