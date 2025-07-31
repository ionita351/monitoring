package com.monitoring.server.grpc.service;

import com.monitoring.server.grpc.mapper.MeasurementMapper;
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
    private static final MeasurementMapper MEASUREMENT_MAPPER = MeasurementMapper.INSTANCE;
    private final MeasurementService measurementService;

    @Override
    public void sendOne(Measurement request, StreamObserver<Response> responseObserver) {
        receive(responseObserver, () -> {
            measurementService.create(MEASUREMENT_MAPPER.toMeasurementDto(request));
        });
    }

    @Override
    public void sendMany(Measurements request, StreamObserver<Response> responseObserver) {
        receive(responseObserver, () -> {
            request.getMessagesList().stream()
                    .map(MEASUREMENT_MAPPER::toMeasurementDto)
                    .forEach(measurementService::create);
        });
    }

    public static void receive(StreamObserver<Response> responseObserver, Runnable executor) {
        Response response;
        try {
            executor.run();
            response = Response.newBuilder()
                    .setSuccess(true)
                    .build();
        } catch (Throwable throwable) {
            response = Response.newBuilder().setSuccess(false)
                    .setErrorMessage(throwable.getLocalizedMessage())
                    .build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
