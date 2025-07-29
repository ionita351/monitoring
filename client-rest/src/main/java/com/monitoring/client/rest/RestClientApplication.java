package com.monitoring.client.rest;

import com.monitoring.client.rest.client.MeasurementRestClient;
import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {

    public static void main(String... args) {
        SpringApplication.run(RestClientApplication.class, args);
    }

    @Autowired
    private MeasurementRestClient client;

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
