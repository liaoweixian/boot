package com.lwx.mqttpublish.controller;


import com.lwx.mqttpublish.config.SendMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/publish")
public class PublishController {

    @Resource
    private SendMessage sendMessage;

    @GetMapping("/demo")
    @ResponseBody
    public String sendDemo() {
        sendMessage.send("就是这个","/com/lwx/boot/qq/99");
        return "发送成功";
    }
}
