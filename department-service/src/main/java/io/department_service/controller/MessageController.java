package io.department_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
//@RequestMapping(value = "/api/message")
public class MessageController {

    @Value("${spring.boot.message}")
    private String message;


    @GetMapping("message")
    public String getMessage(){
        return message;
    }


}
