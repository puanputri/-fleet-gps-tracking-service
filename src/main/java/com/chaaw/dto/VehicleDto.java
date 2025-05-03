package com.chaaw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VehicleDto {
    
    private Long id;
    
    @NotBlank(message = "Plate number is required")
    @Size(max = 20)
    private String plateNumber;
    
    @NotBlank(message = "Vehicle name is required")
    @Size(max = 100)
    private String name;
    
    @Size(max = 50)
    private String type;
    
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}