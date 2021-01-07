package com.example.iot.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my/test")
public class TestRest {

    @GetMapping
    public String hello(@RequestParam("super-param") String friend) {
        return "hello my " + friend;
    }

    @PostMapping
    public String helloPost(@RequestParam("super-param") String friend) {
        return "wooow my " + friend;
    }
}
