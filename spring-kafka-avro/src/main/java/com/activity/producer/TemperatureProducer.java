package com.activity.producer;

import com.activity.dto.TemperatureConversion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TemperatureProducer {

    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, TemperatureConversion> kafkaTemplate;

    public TemperatureProducer(KafkaTemplate<String, TemperatureConversion> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendConversion(double value, String fromUnit, String toUnit) {
        TemperatureConversion conversion = TemperatureConversion.newBuilder()
            .setValue(value)
            .setFromUnit(fromUnit)
            .setToUnit(toUnit)
            .setResult(null)
            .build();

        kafkaTemplate.send(topicName, conversion);
    }
}
