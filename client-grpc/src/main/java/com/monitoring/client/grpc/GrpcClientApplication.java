package com.monitoring.client.grpc;

import com.monitoring.client.grpc.service.GrpcClientService;
import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@ComponentScan("com.monitoring")
public class GrpcClientApplication implements CommandLineRunner {

    public static void main(String... args) {
        SpringApplication.run(GrpcClientApplication.class, args);
    }

    @Autowired
    private GrpcClientService service;

    @Override
    public void run(String... args) throws Exception {
        MeasurementDto DATA = new MeasurementDto(
                UUID.randomUUID(),
                "DN",
                LocalDateTime.now(),
                1D,
                2D,
                true);
        ResponseDto dto = service.sendOne(DATA);
        System.out.println(dto);
        dto = service.sendMany(List.of(DATA));
        System.out.println(dto);
    }
}
