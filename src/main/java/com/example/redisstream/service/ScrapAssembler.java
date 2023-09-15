package com.example.redisstream.service;

import com.example.redisstream.controller.dto.request.CreateScrapRequestDto;
import com.example.redisstream.domain.scrap.ScrapPost;
import com.example.redisstream.domain.scrap.ScrapView;
import com.example.redisstream.domain.user.User;

public class ScrapAssembler {

    private ScrapAssembler() { throw new RuntimeException("Cannot Create ScrapAssembler Instance");}

    public static ScrapPost entityByCreateRequest(User user, CreateScrapRequestDto request) {
        return ScrapPost.builder()
                .user(user)
                .view(new ScrapView())
                .originContentUrl(request.getOriginContentUrl())
                .postTitle(request.getPostTitle())
                .postDescription(request.getPostDescription())
                .build();
    }
}
