package com.chaaw.repository;

import com.chaaw.model.GPSLog;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class GPSLogRepository implements PanacheRepository<GPSLog> {

    public Optional<GPSLog> findLatestByVehicleId(Long vehicleId) {
        return find("vehicle.id = ?1 order by timestamp desc", vehicleId)
                .firstResultOptional();
    }

    public List<GPSLog> findByVehicleIdAndTimestampBetween(Long vehicleId,
                                                           LocalDateTime from,
                                                           LocalDateTime to) {
        return list("vehicle.id = ?1 and timestamp between ?2 and ?3 order by timestamp",
                vehicleId, from, to);
    }
}