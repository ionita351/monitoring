package com.monitoring.client.feign.client;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
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
    public ResponseDto sendOne(MeasurementDto measurement) {
        ResponseEntity<ResponseDto> responseEntity = client.sendOne(measurement);
        return extractBodyFromResponseEntity(responseEntity);
    }

    @Override
    public ResponseDto sendMany(List<MeasurementDto> measurements) {
        ResponseEntity<ResponseDto> responseEntity = client.sendMany(measurements);
        return extractBodyFromResponseEntity(responseEntity);
    }

    private static ResponseDto extractBodyFromResponseEntity(ResponseEntity<ResponseDto> responseEntity) {
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            return ResponseDto.error("Http return code: " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
