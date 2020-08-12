package com.lwx.mapstruct.mapper;

import com.lwx.mapstruct.domain.Person;
import com.lwx.mapstruct.dto.PersonDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonConverter {
    PersonConverter INSTANCE = Mappers.getMapper(PersonConverter.class);

    @Mappings({
            @Mapping(source = "birthday", target = "birth"),
            @Mapping(source = "birthday", target = "birthDateFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "user.age", target = "age", ignore = true),
            @Mapping(target = "email", ignore = true),
    })
    // 设置BeanMapping(ignoreByDefault = true) 代表必须显示指明每一个映射的字断，否者不映射
    @BeanMapping(ignoreByDefault = true)
    PersonDTO domain2dto(Person person);

    List<PersonDTO> domain2dto(List<Person> people);
}
