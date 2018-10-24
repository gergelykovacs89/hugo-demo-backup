package com.hugo.hugodemo.model.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.hugo.hugodemo.model.story.textunit.Text;
import com.hugo.hugodemo.util.View;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TextNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    private Long textNodeID;

    @OneToOne(mappedBy = "textNode", cascade = CascadeType.ALL)
    @JsonView(View.Public.class)
    private Text text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private TextNode parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @JsonView(View.Public.class)
    private Set<TextNode> children = new HashSet<>();

    public TextNode() {
    }

    public TextNode(Text text) {
        text.setTextNode(this);
        this.text = text;
    }

    public Long getTextNodeID() {
        return textNodeID;
    }

    public Text getText() {
        return text;
    }

    public TextNode getParent() {
        return parent;
    }

    private void setParent(TextNode parent) {
        this.parent = parent;
    }

    public Set<TextNode> getChildren() {
        return children;
    }

    public void addChild(TextNode childTextNode) {
        this.children.add(childTextNode);
        childTextNode.setParent(this);
    }
}