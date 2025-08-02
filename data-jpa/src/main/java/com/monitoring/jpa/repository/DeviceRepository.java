package com.monitoring.jpa.repository;

import com.monitoring.jpa.entity.Car;
import com.monitoring.jpa.entity.Device;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID>,
        JpaSpecificationExecutor<Device> {
    Optional<Device> findByDeviceNumber(String deviceNumber);

    interface Spec {

        static Specification<Device> byDeviceNumber(String deviceNumber) {
            return (root, query, builder) ->
                    builder.equal(root.get("deviceNumber"), deviceNumber);
        }

        static Specification<Device> byTimeStampGreaterThen(LocalDateTime from) {
            return (root, query, builder) ->
                    builder.greaterThan(root.join("measurement").get("timeStamp"), from);
        }

        static Specification<Device> byTimeStampLessThen(LocalDateTime from) {
            return (root, query, builder) ->
                    builder.lessThan(root.join("measurement").get("timeStamp"), from);
        }
    }
}
