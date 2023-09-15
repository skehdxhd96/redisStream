package com.example.redisstream.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.time.Duration;
import java.util.UUID;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RedisStreamsConfig {

    @Value("${redis.poll-timeout.image-download}")
    private int imageDownloadPollTimeOut;
    private final StringRedisTemplate redisTemplate;
    private final StreamListener<String, MapRecord<String, String, String>> downloadImageEventStreamListener;
    private static final String INSTANCE = "instance";

    @Bean
    public Subscription imageDownloadSubscription(RedisConnectionFactory factory) {
        final StreamEvent imageDownloadEvent = StreamEvent.IMAGE_DOWNLOAD_EVENT;
        final String eventName = imageDownloadEvent.getEventName();
        final String consumerGroupName = imageDownloadEvent.getConsumerGroupName(EventConsumerGroup.DOWNLOAD);

        registerConsumerGroup(eventName, consumerGroupName);

        return getSubscription(factory, eventName, consumerGroupName, downloadImageEventStreamListener,
                imageDownloadPollTimeOut);
    }

    private Subscription getSubscription(
            final RedisConnectionFactory factory,
            final String eventName,
            final String consumerGroupName,
            final StreamListener<String, MapRecord<String, String, String>> streamListener,
            final int pollTimeoutSeconds
    ) {
        final StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> options =
                StreamMessageListenerContainer
                        .StreamMessageListenerContainerOptions
                        .builder()
                        .pollTimeout(Duration.ofSeconds(pollTimeoutSeconds))
                        .build();

        final StreamMessageListenerContainer<String, MapRecord<String, String, String>> listenerContainer =
                StreamMessageListenerContainer.create(factory, options);

        final Subscription subscription =
                listenerContainer.receiveAutoAck(
                        Consumer.from(consumerGroupName, INSTANCE + ":" + getInstanceId()),
                        StreamOffset.create(eventName, ReadOffset.lastConsumed()),
                        streamListener
                );

        listenerContainer.start();
        return subscription;
    }

    private void registerConsumerGroup(final String eventName, final String consumerGroupName) {
        try {
            redisTemplate.opsForStream().consumers(eventName, consumerGroupName);
            log.info(consumerGroupName + " already exists");
        } catch (Exception e) {
            redisTemplate.opsForStream().createGroup(eventName, consumerGroupName);
        }
    }

    private String getInstanceId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
