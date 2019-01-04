package com.hugo.hugodemo.service;

import com.hugo.hugodemo.model.users.Author;
import com.hugo.hugodemo.model.users.UserProfile;
import com.hugo.hugodemo.repository.AuthorRepository;
import com.hugo.hugodemo.repository.StoryRepository;
import com.hugo.hugodemo.repository.TextNodeRepository;
import com.hugo.hugodemo.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializerBean {

    private final
    AuthorRepository authorRepository;
    private final
    StoryRepository storyRepository;
    private final
    TextNodeRepository textNodeRepository;
    private final
    UserProfileRepository userProfileRepository;

    @Autowired
    public InitializerBean(AuthorRepository authorRepository, StoryRepository storyRepository, TextNodeRepository textNodeRepository, UserProfileRepository userProfileRepository) {
        this.authorRepository = authorRepository;
        this.storyRepository = storyRepository;
        this.textNodeRepository = textNodeRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @PostConstruct
    public void initializerBean() {
        UserProfile user1 = new UserProfile("gergelyk89@gmail.com", "Kovács Gergely");
        Author author1 = new Author(user1, "halvanyretek", "https://f4.bcbits.com/img/a1365259373_10.jpg", "Optimista üzemlakatos");
        Author author2 = new Author(user1, "dömdödöm", "https://i.kym-cdn.com/photos/images/original/000/482/848/a06.png", "“Dude, sucking at sumthin’ is the first step towards being sorta good at something.”");
        UserProfile user2 = new UserProfile("rad008@gmail.com", "Kovács Gergely");
        Author author3 = new Author(user2, "gergo", "https://steamuserimages-a.akamaihd.net/ugc/877498811165618730/D56A1D586D609B68686B7665C6C4D121177B62E8/?imw=1024&imh=575&ima=fit&impolicy=Letterbox&imcolor=%23000000&letterbox=true", "foltos");
        Author author4 = new Author(user2, "tibi", "https://i.redd.it/5ukpb3en98p11.png", "minek");
//        Story story1 = new Story("Retekék ebédre", author1);
//        StoryTree storyTree1 = new StoryTree(story1);
//        Text rootText = new Text("Megettük őket, de nem laktunk jól", author1);
//        Text childText = new Text("Vége", author1);
//        TextNode rootNode = new TextNode(rootText);
//        storyTree1.setRootTextNode(rootNode);
//        TextNode childNode = new TextNode(childText);
//        rootNode.addChild(childNode);
        userProfileRepository.save(user1);
        userProfileRepository.save(user2);

        author1.addFollower(author3);


        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
        authorRepository.save(author4);
//          storyRepository.save(story1);
//
//        Text childText2 = new Text("Boldogan éltünk, míg újra meg nem éheztünk.", author1);
//        TextNode childNode2 = new TextNode(childText2);
//        rootNode.addChild(childNode2);
//
//        textNodeRepository.save(rootNode);
    }
}
