package com.hugo.hugodemo.repository;

import com.hugo.hugodemo.model.story.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface StoryRepository extends JpaRepository<Story, Long> {
    Set<Story> getAllByAuthorId(Long authorId);
}
