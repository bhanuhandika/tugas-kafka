package com.activity.controller;

import com.activity.producer.TemperatureProducer;
import com.activity.request.TemperatureRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TemperatureController {

    @Autowired
    private TemperatureProducer temperatureProducer;

    @PostMapping("/convert-temperature")
    public String convertTemperature(@RequestBody TemperatureRequest request) {
        temperatureProducer.sendConversion(
            request.getValue(),
            request.getFromUnit(),
            request.getToUnit()
        );
        return "Conversion request sent!";
    }
}
