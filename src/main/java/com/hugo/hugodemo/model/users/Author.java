package com.hugo.hugodemo.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.hugo.hugodemo.model.story.Story;
import com.hugo.hugodemo.model.story.textunit.Text;
import com.hugo.hugodemo.util.View;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    @Column(nullable = false)
    @JsonView(View.Public.class)
    private String authorName;

    @OneToMany(mappedBy = "author")
    @JsonView(View.ExtendedPublic.class)
    @JsonIgnore
    private Set<Text> textList = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserProfile userProfile;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<Story> stories = new HashSet<>();

    public Author(UserProfile userProfile, String authorName) {
        this.setUserProfile(userProfile);
        this.authorName = authorName;
    }

    public Author() {
    }

    public Set<Story> getStories() {
        return stories;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    private void setUserProfile(UserProfile userProfile) {
        userProfile.addAlias(this);
        this.userProfile = userProfile;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Set<Text> getTextList() {
        return textList;
    }

    public void addStory(Story story) {
        this.stories.add(story);
    }
}
