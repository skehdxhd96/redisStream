package com.example.redisstream.service;

import com.example.redisstream.controller.dto.request.CreateScrapRequestDto;
import com.example.redisstream.domain.scrap.ScrapPost;
import com.example.redisstream.domain.user.User;
import com.example.redisstream.repository.scrap.ScrapPostRepository;
import com.example.redisstream.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class ScrapPostService {

    private final UserRepository userRepository;

    private final ScrapPostRepository scrapPostRepository;
    private final EventStream downloadEventStream;

    public Long createScrap(CreateScrapRequestDto request) {

        User currentUser = findUserById(request.getUserId());

        ScrapPost scrapPost = ScrapAssembler.entityByCreateRequest(currentUser, request);

        ScrapPost newScrap = scrapPostRepository.save(scrapPost);

        downloadEventStream.publish(createDownloadImageEventMessage(request));

        return newScrap.getId();
    }

    private Map<String, String> createDownloadImageEventMessage(CreateScrapRequestDto request) {
        final HashMap<String, String> fieldMap = new HashMap<>();
        fieldMap.put("originContentUrl", request.getOriginContentUrl());
        return fieldMap;
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
    }
}
