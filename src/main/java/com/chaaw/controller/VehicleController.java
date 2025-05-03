package com.chaaw.controller;


import com.chaaw.dto.VehicleDto;
import com.chaaw.service.VehicleService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/vehicles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehicleController {
    
    @Inject
    VehicleService vehicleService;
    
    @GET
    public List<VehicleDto> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }
    
    @GET
    @Path("/{id}")
    public VehicleDto getVehicle(@PathParam("id") Long id) {
        return vehicleService.getVehicleById(id);
    }
    
    @POST
    public Response createVehicle(@Valid VehicleDto vehicleDto) {
        VehicleDto created = vehicleService.createVehicle(vehicleDto);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }
    
    @PUT
    @Path("/{id}")
    public VehicleDto updateVehicle(@PathParam("id") Long id, @Valid VehicleDto vehicleDto) {
        return vehicleService.updateVehicle(id, vehicleDto);
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteVehicle(@PathParam("id") Long id) {
        vehicleService.deleteVehicle(id);
        return Response.noContent().build();
    }
}