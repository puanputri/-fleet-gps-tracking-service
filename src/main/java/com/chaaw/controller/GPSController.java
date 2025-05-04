package com.chaaw.controller;

import com.chaaw.dto.GPSLogDto;
import com.chaaw.service.GPSLogService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GPSController {
    
    @Inject
    GPSLogService gpsLogService;
    
    @POST
    @Path("/gps")
    public Response createGPSLog(@Valid GPSLogDto gpsLogDto) {
        GPSLogDto created = gpsLogService.createGPSLog(gpsLogDto);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }
    
    @GET
    @Path("/vehicle/{id}/last-location")
    public GPSLogDto getLastLocation(@PathParam("id") Long vehicleId) {
        return gpsLogService.getLatestLocation(vehicleId);
    }
    
    @GET
    @Path("/vehicle/{id}/history")
    public List<GPSLogDto> getLocationHistory(
            @PathParam("id") Long vehicleId,
            @QueryParam("from") String from,
            @QueryParam("to") String to) {
        
        LocalDateTime fromDateTime = LocalDateTime.parse(from);
        LocalDateTime toDateTime = LocalDateTime.parse(to);
        
        return gpsLogService.getLocationHistory(vehicleId, fromDateTime, toDateTime);
    }
}