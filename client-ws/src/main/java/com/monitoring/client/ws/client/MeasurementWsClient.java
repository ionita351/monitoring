package com.monitoring.client.ws.client;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import com.monitoring.service.MeasurementClient;
import com.monitoring.ws.wsdl.ObjectFactory;
import com.monitoring.ws.wsdl.WsMeasurement;
import com.monitoring.ws.wsdl.WsMeasurements;
import com.monitoring.ws.wsdl.WsResponse;
import jakarta.xml.bind.JAXBElement;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.List;

@Component
public class MeasurementWsClient  extends WebServiceGatewaySupport {
    private final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    public ResponseDto sendOne(MeasurementDto measurement) {
        try {
            JAXBElement<WsResponse> wsResponse = (JAXBElement<WsResponse>) getWebServiceTemplate()
                    .marshalSendAndReceive(OBJECT_FACTORY.createReceiveOneRequest(toWsMeasurement(measurement)));
            return toResponseDto(wsResponse.getValue());
        } catch (Throwable throwable) {
            return ResponseDto.error(throwable.getMessage());
        }
    }

    public ResponseDto sendMany(List<MeasurementDto> measurements) {
        List<WsMeasurement> listWsMeasurements = measurements.stream().map(MeasurementWsClient::toWsMeasurement).toList();
        WsMeasurements wsMeasurements = OBJECT_FACTORY.createWsMeasurements();
        wsMeasurements.getMeasurements().addAll(listWsMeasurements);
        JAXBElement<WsResponse> wsResponse = (JAXBElement<WsResponse>) getWebServiceTemplate()
                .marshalSendAndReceive(OBJECT_FACTORY.createReceiveManyRequest(wsMeasurements));
        return toResponseDto(wsResponse.getValue());
    }

    private static WsMeasurement toWsMeasurement(MeasurementDto dto) {
        WsMeasurement wsMeasurement = new WsMeasurement();
        wsMeasurement.setDeviceNumber(dto.getDeviceNumber());
        wsMeasurement.setLatitude(dto.getLatitude());
        wsMeasurement.setLongitude(dto.getLongitude());
        wsMeasurement.setAlert(dto.isAlert());
        return wsMeasurement;
    }

    private static ResponseDto toResponseDto(WsResponse wsResponse) {
        return new ResponseDto(wsResponse.isSuccess(), wsResponse.getErrorMessage());
    }
}
