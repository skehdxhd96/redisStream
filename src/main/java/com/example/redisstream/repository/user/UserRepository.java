package com.example.redisstream.repository.user;

import com.example.redisstream.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
