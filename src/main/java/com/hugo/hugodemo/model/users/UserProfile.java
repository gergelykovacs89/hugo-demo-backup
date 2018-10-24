package com.hugo.hugodemo.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.hugo.hugodemo.util.View;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    private Long userId;

    @Column(nullable = false)
    @JsonView(View.Public.class)
    private String email;

    @Column(nullable = false)
    @JsonView(View.Internal.class)
    private String password;

    @Column(nullable = false)
    @JsonView(View.Public.class)
    private String fullName;

    @OneToMany(mappedBy = "userProfile")
    @JsonView(View.Public.class)
    private Set<Author> aliases;

    public UserProfile(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.aliases = new HashSet<>();
    }

    public UserProfile() {
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public Set<Author> getAliases() {
        return aliases;
    }

    void addAlias(Author alias) {
        this.aliases.add(alias);
    }
}
