package com.levraijmk.deviceservice.service;

import com.levraijmk.deviceservice.dto.DeviceDto;
import com.levraijmk.deviceservice.entity.Device;
import com.levraijmk.deviceservice.repository.DeviceRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviseService {

    private final  DeviceRepository deviceRepository;

    public DeviseService(final DeviceRepository deviceRepository){
        this.deviceRepository = deviceRepository;
    }


    public DeviceDto getDevise(Long id){
        Device device = this.deviceRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Device not found"));
        return deviceToDeviceDto(device);

    }

    private DeviceDto deviceToDeviceDto(Device device){
        return DeviceDto.builder()
                .name(device.getName())
                .id(device.getId())
                .type(device.getType())
                .location(device.getLocation())
                .userId(device.getUserId())
                .build();
    }

    private Device deviceDtoToDevice(DeviceDto deviceDto){
        return Device.builder()
                .name(deviceDto.getName())
                .userId(deviceDto.getUserId())
                .type(deviceDto.getType())
                .location(deviceDto.getLocation())
                .id(deviceDto.getId())
                .build();
    }

    public DeviceDto createDevice(DeviceDto deviceDto) {
        return deviceToDeviceDto(this.deviceRepository.save(deviceDtoToDevice(deviceDto)));
    }
}
