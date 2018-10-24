package com.hugo.hugodemo.model.story.textunit;

public class Tags {

    private Integer tagId;

    private Text text;

    private String tagName;


    public Tags(String tagName, Integer tagId) {
        this.tagName = tagName;
        this.tagId = tagId;
    }

    public Tags() {
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}


