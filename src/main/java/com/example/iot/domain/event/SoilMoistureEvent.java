package com.example.iot.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@FieldDefaults(level = PRIVATE)
@Getter
public class SoilMoistureEvent extends Event {
    String sensor;
    int raw;
    int pct;
}
