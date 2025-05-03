package com.chaaw.service;

import com.chaaw.dto.VehicleDto;
import com.chaaw.mapper.VehicleMapper;
import com.chaaw.model.Vehicle;
import com.chaaw.repository.VehicleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class VehicleService {
    
    @Inject
    VehicleRepository vehicleRepository;
    
    @Inject
    VehicleMapper vehicleMapper;
    
    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.listAll().stream()
                .map(vehicleMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public VehicleDto getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id);
        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found with id: " + id);
        }
        return vehicleMapper.toDto(vehicle);
    }
    
    @Transactional
    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleMapper.toEntity(vehicleDto);
        vehicleRepository.persist(vehicle);
        return vehicleMapper.toDto(vehicle);
    }
    
    @Transactional
    public VehicleDto updateVehicle(Long id, VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleRepository.findById(id);
        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found with id: " + id);
        }
        
        vehicleMapper.updateEntityFromDto(vehicleDto, vehicle);
        vehicleRepository.persist(vehicle);
        return vehicleMapper.toDto(vehicle);
    }
    
    @Transactional
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id);
        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found with id: " + id);
        }
        vehicleRepository.delete(vehicle);
    }
}