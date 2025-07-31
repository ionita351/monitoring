package com.monitoring.client.grpc.mapper;

import com.monitoring.model.ResponseDto;
import com.monitoring.stub.Response;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseMapper {
    ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

    Response toResponse(ResponseDto dto);
    ResponseDto toResponseDto(Response response);
}
