package com.levraijmk.usageservce.service;


import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.levraijmk.sharedevents.event.EnergyUsageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
@Slf4j
public class UsageService {


    private final InfluxDBClient influxDBClient;


    @Value("${influx.bucket}")
    private String influxBucket;

    @Value("${influx.org}")
    private String influxOrg;

    public UsageService(InfluxDBClient influxDBClient) {
        this.influxDBClient = influxDBClient;
    }


    @KafkaListener(topics = "energy-usage", groupId = "usage-service")
    public void energyUsageEvent(EnergyUsageEvent event) {
        System.out.println("🔥--------  MESSAGE RECU à  " +event.timestamp());
        try {
            log.info("Received energy usage event: {}", event);

            Point point = Point.measurement("energy-usage")
                    .addTag("deviceId", String.valueOf(event.deviceId()))
                    .addField("energyConsumed", event.energyConsumed())
                    .time(event.timestamp(), WritePrecision.MS);

            influxDBClient.getWriteApiBlocking()
                    .writePoint(influxBucket, influxOrg, point);

        } catch (Exception e) {
            log.error("Error processing Kafka message", e);
        }
    }



}
