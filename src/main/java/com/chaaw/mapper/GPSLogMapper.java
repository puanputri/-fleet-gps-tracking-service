package com.chaaw.mapper;


import com.chaaw.dto.GPSLogDto;
import com.chaaw.model.GPSLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface GPSLogMapper {
    
    @Mapping(source = "vehicle.id", target = "vehicleId")
    GPSLogDto toDto(GPSLog gpsLog);
    
    @Mapping(target = "vehicle", ignore = true)
    GPSLog toEntity(GPSLogDto gpsLogDto);
}