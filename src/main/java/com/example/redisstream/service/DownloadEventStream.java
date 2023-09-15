package com.example.redisstream.service;

import com.example.redisstream.config.StreamEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class DownloadEventStream implements EventStream{

    private final StringRedisTemplate redisTemplate;

    @Override
    public void publish(Map<String, String> fieldMap) {
        log.info("Image Download Event Stream Executed");
        redisTemplate.opsForStream().add(StreamEvent.IMAGE_DOWNLOAD_EVENT.getEventName(), fieldMap);
        log.info("Image Download Event Stream End");
    }
}
