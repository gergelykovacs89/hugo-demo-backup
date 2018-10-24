package com.hugo.hugodemo.service;

import com.hugo.hugodemo.model.story.Story;
import com.hugo.hugodemo.model.story.textunit.Text;
import com.hugo.hugodemo.model.tree.StoryTree;
import com.hugo.hugodemo.model.tree.TextNode;
import com.hugo.hugodemo.model.users.Author;
import com.hugo.hugodemo.model.users.UserProfile;
import com.hugo.hugodemo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializerBean {

    final
    AuthorRepository authorRepository;
    final
    StoryRepository storyRepository;
    final
    TextNodeRepository textNodeRepository;
    final
    UserProfileRepository userProfileRepository;
    final
    StoryTreeRepository storyTreeRepository;

    @Autowired
    public InitializerBean(AuthorRepository authorRepository, StoryRepository storyRepository, TextNodeRepository textNodeRepository, UserProfileRepository userProfileRepository, StoryTreeRepository storyTreeRepository) {
        this.authorRepository = authorRepository;
        this.storyRepository = storyRepository;
        this.textNodeRepository = textNodeRepository;
        this.userProfileRepository = userProfileRepository;
        this.storyTreeRepository = storyTreeRepository;
    }

    @PostConstruct
    public void initializerBean() {
        UserProfile user1 = new UserProfile("rad008@gmail.com", "password", "gergo");
        Author author1 = new Author(user1, "halvanyretek");
        Story story1 = new Story("Retekék ebédre", author1);
        StoryTree storyTree1 = new StoryTree(story1);
        Text rootText = new Text("Megettük őket, de nem laktunk jól", author1);
        Text childText = new Text("Vége", author1);
        TextNode rootNode = new TextNode(rootText);
        storyTree1.setRootTextNode(rootNode);
        TextNode childNode = new TextNode(childText);
        rootNode.addChild(childNode);
        userProfileRepository.save(user1);
        authorRepository.save(author1);
        storyRepository.save(story1);


        Text childText2 = new Text("Boldogan éltünk, míg újra meg nem éheztünk.", author1);
        TextNode childNode2 = new TextNode(childText2);
        rootNode.addChild(childNode2);

        textNodeRepository.save(rootNode);
    }
}
