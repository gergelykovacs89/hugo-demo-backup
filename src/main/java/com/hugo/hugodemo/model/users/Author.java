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
    @JsonIgnore
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView(View.Public.class)
    private String name;

    @Column(nullable = false)
    @JsonView(View.Public.class)
    private String imgPath;

    @Column(nullable = false)
    @JsonView(View.Public.class)
    private String description;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<Text> textList = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserProfile userProfile;

    @OneToMany(mappedBy = "author")
    @JsonView(View.Public.class)
    @JsonIgnore
    private Set<Story> stories = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "following")
    @JsonView(View.Public.class)
    @JsonIgnoreProperties("following")
    private Set<Author> followers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "followers_following",
            joinColumns = {@JoinColumn(name = "follower_id")},
            inverseJoinColumns = {@JoinColumn(name = "following_id")})
    @JsonView(View.Public.class)
    @JsonIgnoreProperties("followers")
    private Set<Author> following = new HashSet<>();

    public Author(UserProfile userProfile, String name, String imgPath, String description) {
        this.setUserProfile(userProfile);
        this.name = name;
        this.description = description;
        this.imgPath = imgPath;
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Text> getTextList() {
        return textList;
    }

    public void addStory(Story story) {
        this.stories.add(story);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addFollower(Author followerAuthor) {
        this.followers.add(followerAuthor);
        followerAuthor.following.add(this);
    }

    public Set<Author> getFollowers() {
        return followers;
    }

    public Set<Author> getFollowing() {
        return following;
    }
}
