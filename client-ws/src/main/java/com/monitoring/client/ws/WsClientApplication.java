package com.monitoring.client.ws;

import com.monitoring.client.ws.client.MeasurementWsClient;
import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.UUID;

import java.util.List;


@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.monitoring")
public class WsClientApplication implements CommandLineRunner {

    public static void main(String... args) {
        SpringApplication.run(WsClientApplication.class, args);
    }

    @Autowired
    private MeasurementWsClient client;

    @Override
    public void run(String... args) throws Exception {
        MeasurementDto DATA = new MeasurementDto(
                UUID.randomUUID(),
                "DN",
                LocalDateTime.now(),
                1D,
                2D,
                true);
        ResponseDto dto = client.sendOne(DATA);
        System.out.println(dto);

        dto = client.sendMany(List.of(DATA));
        System.out.println(dto);
    }
}
