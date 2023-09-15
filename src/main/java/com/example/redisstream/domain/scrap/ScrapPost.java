package com.example.redisstream.domain.scrap;

import com.example.redisstream.config.BaseEntity;
import com.example.redisstream.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ScrapPost extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "scrap_view_id")
    private ScrapView view;

    private String postTitle;

    private String postDescription;

    private String originContentUrl; // 실제 스크래핑할 이미지의 Url

    private String downloadContentUrl; // 로컬 파일시스템에 위치한 정적파일 서빙 Url

    @Builder
    public ScrapPost(Long id, User user, ScrapView view, String postTitle, String postDescription, String originContentUrl, String downloadContentUrl) {
        this.id = id;
        this.user = user;
        this.view = view;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.originContentUrl = originContentUrl;
        this.downloadContentUrl = downloadContentUrl;
    }
}
