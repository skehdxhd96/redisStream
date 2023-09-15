package com.example.redisstream.domain.scrap;

import com.example.redisstream.config.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ScrapView extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_view_id")
    private Long id;

    private long viewCount;

    public ScrapView() {// 기본생성자를 public으로 열어놔야 할까?
        this.viewCount = 0L;
    }
}
