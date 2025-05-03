package com.chaaw.repository;

import com.chaaw.model.Vehicle;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class VehicleRepository implements PanacheRepository<Vehicle> {

    public Optional<Vehicle> findByPlateNumber(String plateNumber) {
        return find("plateNumber", plateNumber).firstResultOptional();
    }

    public boolean existsById(Long vehicleId) {
        return findByIdOptional(vehicleId).isPresent();
    }
}