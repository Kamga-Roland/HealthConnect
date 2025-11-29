package com.iot.healthconnect.infrastructure.persistence.mapper;

import com.iot.healthconnect.domain.model.Configuration;
import com.iot.healthconnect.infrastructure.persistence.entity.ConfigurationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ConfigurationMapper {
    ConfigurationEntity toEntity(Configuration config);
    Configuration toDomain(ConfigurationEntity entity);
    List<ConfigurationEntity> toEntityList(List<Configuration> configs);
    List<Configuration>  toDomainList(List<ConfigurationEntity> entities);
}
