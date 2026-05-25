package com.levraijmk.ingestionservice.service;

import com.levraijmk.ingestionservice.dto.EnergyUsageDto;
import com.levraijmk.sharedevents.event.EnergyUsageEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IngestionService {
private final KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate;

private IngestionService(final KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate ){
    this.kafkaTemplate = kafkaTemplate;
}

public void ingestionEnergyUsage(EnergyUsageDto energyUsageDto){
    //convertion
    EnergyUsageEvent energyUsageEvent = EnergyUsageEvent.builder()
            .deviceId(energyUsageDto.deviceId())
            .energyConsumed(energyUsageDto.energyConsumed())
            .timestamp(energyUsageDto.timestamp())
            .build();

    //envoie du message à kafka

    kafkaTemplate.send("energy-usage",energyUsageEvent);
   log.info("ingested Energy usage Event {}",energyUsageEvent);

}




}
