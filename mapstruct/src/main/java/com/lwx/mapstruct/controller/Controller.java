package com.lwx.mapstruct.controller;

import com.lwx.mapstruct.domain.Item;
import com.lwx.mapstruct.domain.Person;
import com.lwx.mapstruct.domain.Sku;
import com.lwx.mapstruct.domain.User;
import com.lwx.mapstruct.dto.PersonDTO;
import com.lwx.mapstruct.dto.SkuDTO;
import com.lwx.mapstruct.mapper.ItemConverter;
import com.lwx.mapstruct.mapper.PersonConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/demo")
public class Controller {

    @GetMapping
    @RequestMapping("/test")
    @ResponseBody
    public void test() {
        Person person = new Person(1L,"zhige","zhige.me@gmail.com",new Date(),new User(1));
        PersonDTO personDTO = PersonConverter.INSTANCE.domain2dto(person);
        System.out.println(personDTO);
    }

    @GetMapping
    @RequestMapping("/itemTest")
    @ResponseBody
    public void itemTest() {
        Item item = new Item(1L, "iPhone X");
        Sku sku = new Sku(2L, "phone12345", 1000000);
        SkuDTO skuDTO = ItemConverter.INSTANCE.domain2dto(item, sku);
        System.out.println(skuDTO);
    }

}
