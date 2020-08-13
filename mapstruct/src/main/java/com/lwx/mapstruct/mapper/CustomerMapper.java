package com.lwx.mapstruct.mapper;

import com.lwx.mapstruct.domain.Customer;
import com.lwx.mapstruct.dto.CustomerDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mappings({
            @Mapping(source = "customerName", target = "name")
    })
    Customer toCustomer(CustomerDto customerDto);

    // 反射映射，填充值
    @InheritInverseConfiguration
    CustomerDto toCustomerDto(Customer customer);
}
