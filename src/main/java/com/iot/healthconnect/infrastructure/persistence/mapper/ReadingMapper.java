package com.iot.healthconnect.infrastructure.persistence.mapper;

import com.iot.healthconnect.domain.model.Reading;
import com.iot.healthconnect.infrastructure.persistence.entity.ReadingEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ReadingMapper {
    ReadingEntity toEntity(Reading reading);
    Reading toDomain(ReadingEntity reading);
    List<ReadingEntity> toEntityList(List<Reading> readings);
    List<Reading> toDomainList(List<ReadingEntity> readings);

}
