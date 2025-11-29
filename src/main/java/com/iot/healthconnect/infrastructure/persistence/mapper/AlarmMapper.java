package com.iot.healthconnect.infrastructure.persistence.mapper;

import com.iot.healthconnect.domain.model.Alarm;
import com.iot.healthconnect.infrastructure.persistence.entity.AlarmEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AlarmMapper {
    AlarmEntity toEntity(Alarm alarm);
    Alarm toDomain(AlarmEntity alarmEntity);
    List<AlarmEntity> toEntityList(List<Alarm> alarms);
    List<Alarm> toDomainList(List<AlarmEntity> entities);
}
