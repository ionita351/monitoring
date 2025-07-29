package com.monitoring.client.feign;

import com.monitoring.client.feign.client.MeasurementFeignClient;
import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class FeignClientApplication implements CommandLineRunner {

    public static void main(String... args){
        SpringApplication.run(FeignClientApplication.class, args);
    }

    @Autowired
    private MeasurementFeignClient client;

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
