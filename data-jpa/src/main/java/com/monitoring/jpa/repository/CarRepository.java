package com.monitoring.jpa.repository;

import com.monitoring.jpa.entity.Car;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID>, JpaSpecificationExecutor<Car> {

    interface Spec {
        static Specification<Car> byCarStateSign(String stateSign) {
            return (root, query, builder) ->
                    builder.equal(root.get("stateSign"), stateSign);
        }

        static Specification<Car> byBrand(String brand) {
            return (root, query, builder) ->
                    builder.equal(root.get("brand"), brand);
        }

        static Specification<Car> byDeviceNumber(String deviceNumber) {
            return (root, query, builder) ->
                    builder.equal(root.join("device").get("deviceNumber"), deviceNumber);
        }

        static Specification<Car> byTimeStampGreaterThen(LocalDateTime from) {
            return (root, query, builder) ->
                    builder.greaterThan(root.join("device").join("measurement").get("timeStamp"), from);
        }

        static Specification<Car> byTimeStampLessThen(LocalDateTime from) {
            return (root, query, builder) ->
                    builder.lessThan(root.join("device").join("measurement").get("timeStamp"), from);
        }
    }
}
