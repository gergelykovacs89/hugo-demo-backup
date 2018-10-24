package com.hugo.hugodemo.repository;

import com.hugo.hugodemo.model.users.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
