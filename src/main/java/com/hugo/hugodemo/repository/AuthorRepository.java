package com.hugo.hugodemo.repository;

import com.hugo.hugodemo.model.users.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
