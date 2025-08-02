package com.monitoring.jpa.service;

import com.monitoring.jpa.entity.Car;
import com.monitoring.jpa.mapper.CarMapper;
import com.monitoring.jpa.repository.CarRepository;
import com.monitoring.model.CarDto;
import com.monitoring.model.CarDtoRequest;
import com.monitoring.service.DataCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DataCarServiceImpl implements DataCarService {
    private static final CarMapper MAPPER = CarMapper.INSTANCE;

    private final CarRepository carRepository;

    @Override
    public Optional<CarDto> findById(UUID id) {
        return carRepository.findById(id).map(MAPPER::carToCarDto);
    }

    @Override
    public CarDto create(CarDto car) {
        return MAPPER.carToCarDto(carRepository.save(MAPPER.carDtoToCar(car)));
    }

    public List<CarDto> findAllByRequest(CarDtoRequest request) {
        List<Specification<Car>> specifications = new ArrayList<>();
        if (request != null) {
            if (request.getStateSign() != null) {
                specifications.add(CarRepository.Spec.byCarStateSign(request.getStateSign()));
            }
            if (request.getBrand() != null) {
                specifications.add(CarRepository.Spec.byBrand(request.getBrand()));
            }
            if (request.getDeviceNumber() != null) {
                specifications.add(CarRepository.Spec.byDeviceNumber(request.getDeviceNumber()));
            }
            if (request.getTimeStampFrom() != null) {
                specifications.add(CarRepository.Spec.byTimeStampGreaterThen(request.getTimeStampFrom()));
            }
            if (request.getTimeStampTo() != null) {
                specifications.add(CarRepository.Spec.byTimeStampLessThen(request.getTimeStampTo()));
            }
        }
        return carRepository.findAll(specifications.isEmpty()
                ? null
                : Specification.allOf(specifications)).stream()
                .map(MAPPER::carToCarDto).toList();
    }
}
