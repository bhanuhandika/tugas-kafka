package com.activity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureRequest {
    private double value;
    private String fromUnit;
    private String toUnit;
}
