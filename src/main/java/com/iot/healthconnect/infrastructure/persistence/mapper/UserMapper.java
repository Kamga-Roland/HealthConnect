package com.iot.healthconnect.infrastructure.persistence.mapper;

import com.iot.healthconnect.domain.model.User;
import com.iot.healthconnect.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserEntity toEntity(User user);
    User toDomain(UserEntity userEntity);
    List<UserEntity> toEntityList(List<User> users);
    List<User> toDomainList(List<UserEntity> entities);
}
