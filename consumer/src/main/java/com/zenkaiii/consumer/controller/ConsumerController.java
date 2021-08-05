package com.zenkaiii.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ConsumerController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/instances")
    public List<ServiceInstance> instances(){
        List<ServiceInstance> provider = this.discoveryClient.getInstances("provider");
        return provider;
    }

//    @GetMapping("/index")
//    public String index(){
//        List<ServiceInstance> list = this.discoveryClient.getInstances("provider");
//        ServiceInstance serviceInstance = list.get(0);
//        String url = serviceInstance.getUri()+"/index";
//
//        return "调用了端口为："+serviceInstance.getPort()+"的服务，返回结果："+this.restTemplate.getForObject(url,String.class);
//    }

    @GetMapping("/index")
    public String index(){
        return this.restTemplate.getForObject("http://provider/index",String.class);
    }

}
