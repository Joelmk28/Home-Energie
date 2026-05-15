package com.levraijmk.deviceservice.repository;

import com.levraijmk.deviceservice.entity.Device;
import com.levraijmk.deviceservice.model.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,Long> {
}
