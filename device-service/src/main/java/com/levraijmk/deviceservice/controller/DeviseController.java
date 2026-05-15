package com.levraijmk.deviceservice.controller;

import com.levraijmk.deviceservice.dto.DeviceDto;
import com.levraijmk.deviceservice.entity.Device;
import com.levraijmk.deviceservice.service.DeviseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/device")
public class DeviseController {



    public final DeviseService deviseService;

    public DeviseController(final DeviseService deviseService){
        this.deviseService = deviseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getDevice(@PathVariable Long id){
        try{
            DeviceDto deviceDto =  this.deviseService.getDevise(id);
            return ResponseEntity.ok(deviceDto);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto deviceDto){
        DeviceDto createdDevice = this.deviseService.createDevice(deviceDto);
        return new ResponseEntity<>(createdDevice,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDto> updateDevice(@PathVariable Long id, @RequestBody DeviceDto deviceDto){
        try {
            DeviceDto device = this.deviseService.updateDevice(id,deviceDto);
            return ResponseEntity.ok(device);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
