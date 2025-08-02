package com.monitoring.jpa.repository;

import com.monitoring.jpa.entity.Car;
import com.monitoring.jpa.entity.Device;
import com.monitoring.jpa.entity.Measurement;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, UUID>,
        JpaSpecificationExecutor<Measurement> {

    interface Spec {

        static Specification<Measurement> byDeviceNumber(String deviceNumber) {
            return (root, query, builder) ->
                    builder.equal(root.get("deviceNumber"), deviceNumber);
        }

        static Specification<Measurement> byTimeStampGreaterThen(LocalDateTime from) {
            return (root, query, builder) ->
                    builder.greaterThan(root.get("timeStamp"), from);
        }

        static Specification<Measurement> byTimeStampLessThen(LocalDateTime from) {
            return (root, query, builder) ->
                    builder.lessThan(root.get("timeStamp"), from);
        }
    }
}
