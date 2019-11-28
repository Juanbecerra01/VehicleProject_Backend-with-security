package com.vehicle.vehicle.rest;

import com.sun.deploy.net.HttpResponse;
import com.vehicle.vehicle.entity.Vehicle;
import com.vehicle.vehicle.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleRestController {
    
    @Autowired
    private VehicleService vehicleService;
    
    @GetMapping("/vehicles")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<Vehicle> getVehicles(){
        return vehicleService.getVehicles();
    }
    
    @GetMapping("/vehicles/{vehicleId}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public Vehicle getVehicle(@PathVariable int vehicleId) throws Exception {
        Vehicle vehicle = vehicleService.getVehicle(vehicleId);
        if(vehicle == null){
            throw new Exception("Vehicle not found");
        }
        return vehicle;
    }

    @PostMapping("/vehicles")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle){
        vehicle.setId(0);
        vehicleService.saveVehicle(vehicle);
        return vehicle;
    }
    
    @PutMapping("/vehicles")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public Vehicle updateVehicle(@RequestBody Vehicle vehicle){
        vehicleService.saveVehicle(vehicle);
        return vehicle;
    }
    
    @DeleteMapping("/vehicles/{vehicleId}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public void deleteVehicle(@PathVariable int vehicleId) throws Exception {
        Vehicle v = vehicleService.getVehicle(vehicleId);
        if(v == null) {
            throw new Exception("Error");
        }
        vehicleService.deleteVehicle(vehicleId);
    }
}