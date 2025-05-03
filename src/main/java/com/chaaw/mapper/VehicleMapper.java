package com.chaaw.mapper;

import com.chaaw.dto.VehicleDto;
import com.chaaw.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface VehicleMapper {
    
    VehicleDto toDto(Vehicle vehicle);
    
    Vehicle toEntity(VehicleDto vehicleDto);
    
    void updateEntityFromDto(VehicleDto vehicleDto, @MappingTarget Vehicle vehicle);
}