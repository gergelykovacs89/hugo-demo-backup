package com.hugo.hugodemo.repository;

import com.hugo.hugodemo.model.tree.TextNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextNodeRepository extends JpaRepository<TextNode, Long> {
}
