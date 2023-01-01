package com.example.wegoogift.mapper;

import com.example.wegoogift.model.dto.DepositDTO;
import com.example.wegoogift.model.entity.CompanyEntity;
import com.example.wegoogift.model.entity.DepositEntity;
import com.example.wegoogift.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface DepositMapper {

    DepositDTO toDepositDTO(DepositEntity depositEntity);

    List<DepositDTO> toDepositDTOList(List<DepositEntity> employees);

    @Mapping(source = "companyEntity",target = "company")
    @Mapping(source = "userEntity",target = "user")
    @Mapping(target = "id", ignore = true)
    DepositEntity toDepositEntity(DepositDTO depositDTO, CompanyEntity companyEntity, UserEntity userEntity);
}
