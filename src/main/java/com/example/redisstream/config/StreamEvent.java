package com.example.redisstream.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum StreamEvent {

    IMAGE_DOWNLOAD_EVENT("IMAGE_DOWNLOAD_EVENT", List.of("originContentUrl") , List.of(EventConsumerGroup.DOWNLOAD));

    final String eventName;
    final List<String> parameterKeys;
    final List<EventConsumerGroup> consumerGroups;

    public String getConsumerGroupName(EventConsumerGroup consumerGroup) {
        return this.eventName + ":" + this.consumerGroups.stream()
                .filter(cg -> cg.equals(consumerGroup))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot Found Consumer Group Matching Event"))
                .getGroupName();
    }
}
