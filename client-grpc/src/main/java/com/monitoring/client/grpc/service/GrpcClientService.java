package com.monitoring.client.grpc.service;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import com.monitoring.service.MeasurementClient;
import com.monitoring.stub.Measurement;
import com.monitoring.stub.MeasurementStubProviderGrpc;
import com.monitoring.stub.Measurements;
import com.monitoring.stub.Response;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrpcClientService implements MeasurementClient {

    @GrpcClient("client")
    private MeasurementStubProviderGrpc.MeasurementStubProviderBlockingStub stub;

    @Override
    public ResponseDto sendOne(MeasurementDto measurementDto) {
        try {
            Response response = stub.sendOne(toMeasurement(measurementDto));
            return toResponseDto(response);
        } catch (Throwable throwable) {
            return ResponseDto.error(throwable.getMessage());
        }
    }

    @Override
    public ResponseDto sendMany(List<MeasurementDto> measurementDtos) {
        try {
            Measurements measurements = Measurements.newBuilder()
                    .addAllMessages(measurementDtos.stream()
                            .map(GrpcClientService::toMeasurement).toList())
                    .build();
            Response response = stub.sendMany(measurements);
            return toResponseDto(response);
        } catch (Throwable throwable) {
            return ResponseDto.error(throwable.getMessage());
        }
    }

    private static Measurement toMeasurement(MeasurementDto dto) {
        return Measurement.newBuilder()
                .setDeviceNumber(dto.getDeviceNumber())
                .setLatitude(dto.getLatitude())
                .setLongitude(dto.getLongitude())
                .setAlert(dto.isAlert())
                .build();
    }

    private static ResponseDto toResponseDto(Response response)  {
        return new ResponseDto(response.getSuccess(), response.getErrorMessage());
    }
}
