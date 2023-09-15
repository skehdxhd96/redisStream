package com.example.redisstream.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateScrapRequestDto {

    private Long userId; // 사용자 구분 용. 실 서비스에서는 필요 없는 필드.

    private String postTitle;
    private String postDescription;
    private String originContentUrl;

    public CreateScrapRequestDto(Long userId, String postTitle, String postDescription, String originContentUrl) {
        this.userId = userId;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.originContentUrl = originContentUrl;
    }
}
