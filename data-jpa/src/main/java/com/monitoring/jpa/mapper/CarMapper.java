package com.monitoring.jpa.mapper;

import com.monitoring.jpa.entity.Car;
import com.monitoring.model.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDto carToCarDto(Car car);
    Car carDtoToCar(CarDto carDto);
}
