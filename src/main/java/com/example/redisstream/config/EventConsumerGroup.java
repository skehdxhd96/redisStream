package com.example.redisstream.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventConsumerGroup {

    DOWNLOAD("DOWNLOAD");

    final String groupName;
}
