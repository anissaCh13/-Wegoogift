package com.example.wegoogift.mapper;

import com.example.wegoogift.model.dto.UserDTO;
import com.example.wegoogift.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDTO toUserDTO(UserEntity userEntity);
}
