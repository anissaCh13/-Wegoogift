package com.example.wegoogift.mapper;

import com.example.wegoogift.model.dto.CompanyDTO;
import com.example.wegoogift.model.dto.UserDTO;
import com.example.wegoogift.model.entity.CompanyEntity;
import com.example.wegoogift.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CompanyMapper {

    @Mapping(source = "depositEntities", target = "depositDTOS")
    @Mapping(source = "users", target = "userDTOS")
    CompanyDTO toCompanyDTO(CompanyEntity companyEntity);

    @Mapping(source = "depositEntities", target = "giftDepositDTO")
    UserDTO toUserDTO(UserEntity userEntity);

    List<CompanyDTO> toCompanyDTOList(List<CompanyEntity> companyEntities);

}
