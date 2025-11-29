package com.iot.healthconnect.infrastructure.persistence.mapper;

import com.iot.healthconnect.domain.model.Device;
import com.iot.healthconnect.infrastructure.persistence.entity.DeviceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DeviceMapper {
    DeviceEntity toEntity(Device device);
    Device toDomain(DeviceEntity device);
    List<DeviceEntity> toEntityList(List<Device> devices);
    List<Device> toDomainList(List<DeviceEntity> entities);
}
