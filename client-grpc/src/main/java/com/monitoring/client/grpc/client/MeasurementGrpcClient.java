package com.monitoring.client.grpc.client;

import com.monitoring.client.grpc.mapper.MeasurementMapper;
import com.monitoring.client.grpc.mapper.ResponseMapper;
import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import com.monitoring.service.MeasurementClient;
import com.monitoring.stub.MeasurementStubProviderGrpc;
import com.monitoring.stub.Measurements;
import com.monitoring.stub.Response;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementGrpcClient implements MeasurementClient {
    private static final MeasurementMapper MEASUREMENT_MAPPER = MeasurementMapper.INSTANCE;
    private static final ResponseMapper RESPONSE_MAPPER = ResponseMapper.INSTANCE;

    @GrpcClient("client")
    private MeasurementStubProviderGrpc.MeasurementStubProviderBlockingStub stub;

    @Override
    public ResponseDto sendOne(MeasurementDto measurementDto) {
        try {
            Response response = stub.sendOne(MEASUREMENT_MAPPER.toMeasurement(measurementDto));
            return RESPONSE_MAPPER.toResponseDto(response);
        } catch (Throwable throwable) {
            return ResponseDto.error(throwable.getMessage());
        }
    }

    @Override
    public ResponseDto sendMany(List<MeasurementDto> measurementDtos) {
        try {
            Measurements measurements = Measurements.newBuilder()
                    .addAllMessages(measurementDtos.stream()
                            .map(MEASUREMENT_MAPPER::toMeasurement).toList())
                    .build();
            Response response = stub.sendMany(measurements);
            return RESPONSE_MAPPER.toResponseDto(response);
        } catch (Throwable throwable) {
            return ResponseDto.error(throwable.getMessage());
        }
    }
}
