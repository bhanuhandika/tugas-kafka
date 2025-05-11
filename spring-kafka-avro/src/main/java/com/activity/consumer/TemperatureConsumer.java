package com.activity.consumer;

import com.activity.dto.TemperatureConversion;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class TemperatureConsumer {

    @KafkaListener(
    topics = "${topic.name}",
    groupId = "temp-conversion-group",
    containerFactory = "kafkaListenerContainerFactory"
)
    public void consume(ConsumerRecord<String, TemperatureConversion> record) {
        TemperatureConversion conversion = record.value();
        double result = 0.0;

        switch (conversion.getFromUnit().toString().toLowerCase() + "-" + conversion.getToUnit().toString().toLowerCase()) {
            case "celsius-fahrenheit":
                result = (conversion.getValue() * 9/5) + 32;
                break;
            case "fahrenheit-celsius":
                result = (conversion.getValue() - 32) * 5/9;
                break;
            case "celsius-kelvin":
                result = conversion.getValue() + 273.15;
                break;
            case "kelvin-celsius":
                result = conversion.getValue() - 273.15;
                break;
            case "fahrenheit-kelvin":
                result = (conversion.getValue() - 32) * 5/9 + 273.15;
                break;
            case "kelvin-fahrenheit":
                result = (conversion.getValue() - 273.15) * 9/5 + 32;
                break;
            default:
                System.out.println("Unsupported conversion.");
                return;
        }

        conversion = TemperatureConversion.newBuilder()
            .setValue(conversion.getValue())
            .setFromUnit(conversion.getFromUnit())
            .setToUnit(conversion.getToUnit())
            .setResult(result)
            .build();

        System.out.println("Conversion Result: " + conversion.getResult());
    }
}
