package com.example.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// this class will service Rest(http) Apis
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/say/{name}")
    public String sayHello(@PathVariable("name") String name){
        String finalString= "";
        for(int i=0;i<100;i++){
            finalString+="Hello " + name;
            finalString += "<br>";
        }
        return finalString;
    }
}
