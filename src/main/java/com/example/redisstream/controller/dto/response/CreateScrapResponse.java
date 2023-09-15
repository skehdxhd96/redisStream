package com.example.redisstream.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateScrapResponse {

    private Long scrapPostId;

    public CreateScrapResponse(Long scrapPostId) {
        this.scrapPostId = scrapPostId;
    }
}
