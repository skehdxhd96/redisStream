package com.example.redisstream.service;

import java.util.Map;

public interface EventStream {

    void publish(Map<String, String> fieldMap);
}
