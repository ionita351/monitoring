package com.monitoring.client.ws.config;

import com.monitoring.client.ws.client.MeasurementWsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WsClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.monitoring.ws.wsdl");
        return marshaller;
    }
    @Bean
    public MeasurementWsClient measurementWsClient(Jaxb2Marshaller marshaller) {
        MeasurementWsClient client = new MeasurementWsClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
