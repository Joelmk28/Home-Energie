package com.levraijmk.deviceservice.service;

import com.levraijmk.deviceservice.dto.DeviceDto;
import com.levraijmk.deviceservice.entity.Device;
import com.levraijmk.deviceservice.exception.DeviceNotFoundException;
import com.levraijmk.deviceservice.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviseService {

    private final  DeviceRepository deviceRepository;

    public DeviseService(final DeviceRepository deviceRepository){
        this.deviceRepository = deviceRepository;
    }


    public DeviceDto getDevise(Long id){
        return deviceToDeviceDto(this.deviceRepository.findById(id)
                .orElseThrow(()-> new DeviceNotFoundException("Device Not Found "+id))
        );

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

    public DeviceDto updateDevice(Long id,DeviceDto deviceDto) {
        Device deviceFound = this.deviceRepository.findById(id)
                .orElseThrow(()-> new DeviceNotFoundException("Device Not Found "+ id));

        deviceFound.setName(deviceDto.getName());
        deviceFound.setType(deviceDto.getType());
        deviceFound.setLocation(deviceDto.getLocation());

       return deviceToDeviceDto(this.deviceRepository.save(deviceFound));

    }

    public void deleteDevice(Long id) {
        Device deviceFound = this.deviceRepository.findById(id)
                .orElseThrow(()-> new DeviceNotFoundException("Device Not Found "+ id));
        this.deviceRepository.delete(deviceFound);
    }


}
