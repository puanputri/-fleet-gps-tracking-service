package com.chaaw.service;

import com.chaaw.dto.GPSLogDto;
import com.chaaw.mapper.GPSLogMapper;
import com.chaaw.model.GPSLog;
import com.chaaw.model.Vehicle;
import com.chaaw.repository.GPSLogRepository;
import com.chaaw.repository.VehicleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GPSLogService {
    
    @Inject
    GPSLogRepository gpsLogRepository;
    
    @Inject
    VehicleRepository vehicleRepository;
    
    @Inject
    GPSLogMapper gpsLogMapper;
    
    @Transactional
    public GPSLogDto createGPSLog(GPSLogDto gpsLogDto) {
        Vehicle vehicle = vehicleRepository.findById(gpsLogDto.getVehicleId());
        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found with id: " + gpsLogDto.getVehicleId());
        }
        
        GPSLog gpsLog = gpsLogMapper.toEntity(gpsLogDto);
        gpsLog.setVehicle(vehicle);
        
        if (gpsLog.getTimestamp() == null) {
            gpsLog.setTimestamp(LocalDateTime.now());
        }
        
        gpsLogRepository.persist(gpsLog);
        return gpsLogMapper.toDto(gpsLog);
    }
    
    public GPSLogDto getLatestLocation(Long vehicleId) {
        return gpsLogRepository.findLatestByVehicleId(vehicleId)
                .map(gpsLogMapper::toDto)
                .orElseThrow(() -> new NotFoundException("No GPS logs found for vehicle id: " + vehicleId));
    }
    
    public List<GPSLogDto> getLocationHistory(Long vehicleId, LocalDateTime from, LocalDateTime to) {
        if (!vehicleRepository.existsById(vehicleId)) {
            throw new NotFoundException("Vehicle not found with id: " + vehicleId);
        }
        
        return gpsLogRepository.findByVehicleIdAndTimestampBetween(vehicleId, from, to)
                .stream()
                .map(gpsLogMapper::toDto)
                .collect(Collectors.toList());
    }
}