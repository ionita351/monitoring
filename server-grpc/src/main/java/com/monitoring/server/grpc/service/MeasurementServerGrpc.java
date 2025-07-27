package com.monitoring.server.grpc.service;

import com.monitoring.model.MeasurementDto;
import com.monitoring.server.grpc.converter.MeasurementConverter;
import com.monitoring.service.MeasurementService;
import com.monitoring.stub.Measurement;
import com.monitoring.stub.MeasurementStubProviderGrpc;
import com.monitoring.stub.Measurements;
import com.monitoring.stub.Response;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class MeasurementServerGrpc extends MeasurementStubProviderGrpc.MeasurementStubProviderImplBase {
    private final MeasurementService measurementService;

    @Override
    public void sendOne(Measurement request, StreamObserver<Response> responseObserver) {
        try {

            measurementService.create(MeasurementConverter.toMeasurementDto(request));

            Response response = Response.newBuilder()
                    .setSuccess(true)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Throwable throwable) {
            Response response = Response.newBuilder()
                    .setSuccess(false)
                    .setErrorMessage(throwable.getLocalizedMessage())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void sendMany(Measurements request, StreamObserver<Response> responseObserver) {
        try {
            request.getMessagesList().stream()
                    .map(MeasurementServerGrpc::toDto)
                    .forEach(e -> measurementService.create(e));
            Response response = Response.newBuilder()
                    .setSuccess(true)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Throwable throwable) {
            Response response = Response.newBuilder()
                    .setSuccess(false)
                    .setErrorMessage(throwable.getLocalizedMessage())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

    }

    private static MeasurementDto toDto(Measurement measurement) {
        MeasurementDto dto = new MeasurementDto();
        dto.setDeviceNumber(measurement.getDeviceNumber());
        dto.setLatitude(measurement.getLatitude());
        dto.setLongitude(measurement.getLongitude());
        dto.setAlert(measurement.getAlert());
        return dto;
    }
}
