package com.demo.apigateway.servers;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class DemoController {

    @GetMapping("/service1/test1")
    @ResponseBody
    public String service1(HttpServletRequest request) {
        return "service 1 process GET with params:: " + request.getQueryString();
    }

    @PostMapping("/service2/test2")
    @ResponseBody
    public String service2(@RequestBody String body, HttpMethod method, HttpServletRequest request) {
        return "service 2 process POST with params:: " + body;
    }

    @PatchMapping("/service3/test3")
    @ResponseBody
    public String service3(@RequestBody String body, HttpMethod method, HttpServletRequest request) {
        return "service 2 process PATCH with params:: " + body;
    }

}
