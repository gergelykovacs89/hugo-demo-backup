package com.hugo.hugodemo.repository;

import com.hugo.hugodemo.model.users.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Author[] getAuthorsByUserProfileUserId(long userId);

    public Author getAuthorByName(String name);

    @Transactional
    public void deleteAuthorByName(String name);
}
