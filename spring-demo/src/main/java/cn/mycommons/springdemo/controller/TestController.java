package cn.mycommons.springdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/test")
    public Object test() {
        Map<String, Object> map = new HashMap<>();
        map.put("hello", "world");
        return map;
    }
}