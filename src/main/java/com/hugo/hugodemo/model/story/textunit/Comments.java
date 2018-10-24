package com.hugo.hugodemo.model.story.textunit;


public class Comments {

    private String commentId;

    private Text text;

    private String authorId;
    private String comment;

    public Comments(String textSampleId, String authorId, String comment) {
        this.commentId = textSampleId;
        this.authorId = authorId;
        this.comment = comment;
    }

    public Comments() {
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
