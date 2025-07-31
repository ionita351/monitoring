package com.monitoring.client.ws.mapper;

import com.monitoring.model.ResponseDto;
import com.monitoring.ws.wsdl.WsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseMapper {
    ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

    WsResponse toWsResponse(ResponseDto dto);
    ResponseDto toResponseDto(WsResponse wsResponse);
}
