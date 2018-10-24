package com.hugo.hugodemo.model.story.textunit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.hugo.hugodemo.model.tree.TextNode;
import com.hugo.hugodemo.model.users.Author;
import com.hugo.hugodemo.util.View;

import javax.persistence.*;


@Entity
public class Text {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    private Long textId;

    @Column(nullable = false)
    @JsonView(View.Public.class)
    private String textett;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonView(View.Public.class)
    private Author author;

    @OneToOne
    @JoinColumn(name = "node_id")
    @JsonIgnore
    private TextNode textNode;


    public Text(String textett, Author author) {
        this.textett = textett;
        this.author = author;
    }

    public Text() {
    }

    public Long getTextId() {
        return textId;
    }

    public String getTextett() {
        return textett;
    }

    public Author getAuthor() {
        return author;
    }

    public TextNode getTextNode() {
        return textNode;
    }

    public void setTextNode(TextNode textNode) {
        this.textNode = textNode;
    }

    @Override
    public String toString() {
        return this.textett;
    }
}
