package com.example.redisstream.repository.scrap;

import com.example.redisstream.domain.scrap.ScrapPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapPostRepository extends JpaRepository<ScrapPost, Long> {
}
