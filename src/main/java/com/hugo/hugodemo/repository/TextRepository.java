package com.hugo.hugodemo.repository;

import com.hugo.hugodemo.model.story.textunit.Text;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository extends JpaRepository<Text, Long> {
}
