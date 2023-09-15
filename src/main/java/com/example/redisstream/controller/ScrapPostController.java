package com.example.redisstream.controller;

import com.example.redisstream.controller.dto.request.CreateScrapRequestDto;
import com.example.redisstream.controller.dto.response.CreateScrapResponse;
import com.example.redisstream.service.ScrapPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scrap")
public class ScrapPostController {

    private final ScrapPostService scrapPostService;

    @GetMapping("/test")
    public String test() throws IOException {
        return "test";
    }

    @PostMapping
    public ResponseEntity<CreateScrapResponse> createScrap(
        @RequestBody CreateScrapRequestDto request
    ) {
        Long newScrap = scrapPostService.createScrap(request);

        return ResponseEntity.ok(new CreateScrapResponse(newScrap));
    }
}
