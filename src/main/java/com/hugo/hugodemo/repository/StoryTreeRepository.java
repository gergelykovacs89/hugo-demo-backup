package com.hugo.hugodemo.repository;

import com.hugo.hugodemo.model.tree.StoryTree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryTreeRepository extends JpaRepository<StoryTree, Long> {
}
