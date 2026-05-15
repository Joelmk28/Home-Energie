package com.levraijmk.deviceservice.controller;

import com.levraijmk.deviceservice.dto.DeviceDto;
import com.levraijmk.deviceservice.service.DeviseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/device")
public class DeviseController {



    public final DeviseService deviseService;

    public DeviseController(final DeviseService deviseService){
        this.deviseService = deviseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getDevice(@PathVariable Long id){
       DeviceDto deviceDto =  this.deviseService.getDevise(id);
       return ResponseEntity.ok(deviceDto);
    }
}
