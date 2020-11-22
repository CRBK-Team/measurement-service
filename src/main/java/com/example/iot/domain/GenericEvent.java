package com.example.iot.domain;

import lombok.Value;

@Value
public class GenericEvent {
    String type;
    String object;
}
