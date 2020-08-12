package com.lwx.mapstruct;

import com.lwx.mapstruct.domain.Person;
import com.lwx.mapstruct.domain.User;
import com.lwx.mapstruct.dto.PersonDTO;
import com.lwx.mapstruct.mapper.PersonConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MapstructApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void PersonTest() {
		Person person = new Person(1L,"zhige","zhige.me@gmail.com",new Date(),new User(1));
		PersonDTO personDTO = PersonConverter.INSTANCE.domain2dto(person);
		System.out.println(personDTO);
	}

}
