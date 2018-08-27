package cn.itcast.service;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@SpringBootApplication
//@EnableDiscoveryClient // 开启Eureka客户端
//@EnableCircuitBreaker //开启熔断
@SpringCloudApplication
@EnableFeignClients //开启Feign客户端
public class ItcastServiceConsumerApplication {

    /*@Bean
    @LoadBalanced //开启负载均衡
    public RestTemplate restTemplate(){
        //这次我们使用了Okhttp客户端，只需要注入工厂即可
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ItcastServiceConsumerApplication.class, args);
    }
}
