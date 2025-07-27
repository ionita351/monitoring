package com.monitoring.client.feign.client;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.Response;
import com.monitoring.service.MeasurementClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementFeignClient implements MeasurementClient {

    @Autowired
    private MeasurementFeignContract client;

    @Override
    public Response sendOne(MeasurementDto measurement) {
        ResponseEntity<Response> responseEntity = client.sendOne(measurement);
        return extractBodyFromResponseEntity(responseEntity);
    }

    @Override
    public Response sendMany(List<MeasurementDto> measurements) {
        ResponseEntity<Response> responseEntity = client.sendMany(measurements);
        return extractBodyFromResponseEntity(responseEntity);
    }

    private static Response extractBodyFromResponseEntity(ResponseEntity<Response> responseEntity) {
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            return Response.error("Http return code: " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
