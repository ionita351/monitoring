package com.monitoring.ws.server.endpoint;

import com.monitoring.model.MeasurementDto;
import com.monitoring.service.MeasurementService;
import jakarta.xml.bind.JAXBElement;
import lombok.RequiredArgsConstructor;
import monitoring.com.measurement.ObjectFactory;
import monitoring.com.measurement.WsMeasurement;
import monitoring.com.measurement.WsMeasurements;
import monitoring.com.measurement.WsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
@RequiredArgsConstructor
public class MeasurementEndpoint {
    private static final String NAMESPACE_URI = "http://com.monitoring/measurement";
    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    private final MeasurementService measurementService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "receiveOneRequest")
    @ResponsePayload
    public JAXBElement<WsResponse> receiveOne(@RequestPayload JAXBElement<WsMeasurement> request) {
        return receive(() -> {
            measurementService.create(toMeasurementDto(request.getValue()));
        });
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "receiveManyRequest")
    @ResponsePayload
    public JAXBElement<WsResponse> receiveMany(@RequestPayload JAXBElement<WsMeasurements> request) {
        return receive(() -> {
            request.getValue().getMeasurement().forEach(wsMeasurement -> {
                measurementService.create(toMeasurementDto(wsMeasurement));
            });
        });
    }

    public JAXBElement<WsResponse> receive(Runnable executor) {
        WsResponse response = new WsResponse();
        try {
            executor.run();
            response.setSuccess(true);
        } catch (Throwable throwable) {
            response.setSuccess(false);
            response.setErrorMessage(throwable.getMessage());
        }
        return OBJECT_FACTORY.createReceiveManyResponse(response);
    }

    private static MeasurementDto toMeasurementDto(WsMeasurement wsMeasurement) {
        MeasurementDto dto = new MeasurementDto();
        dto.setDeviceNumber(wsMeasurement.getDeviceNumber());
        dto.setLatitude(wsMeasurement.getLatitude());
        dto.setLongitude(wsMeasurement.getLongitude());
        dto.setAlert(wsMeasurement.isAlert());
        return dto;
    }
}
