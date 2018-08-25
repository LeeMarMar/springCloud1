package cn.itcast.service.dao;

import cn.itcast.service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class UserDao {

    @Autowired
    private RestTemplate restTemplate;

    // eureka客户端，可以获取到eureka中服务的信息
    @Autowired
    private DiscoveryClient discoveryClient;

    public User queryUserById(Long id) {
        // 根据服务名称，获取服务实例。有可能是集群，所以是service实例集合
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        // 因为只有一个UserService。所以获取第一个实例
        ServiceInstance instance = instances.get(0);
        // 获取ip和端口信息，拼接成服务地址
        String baseUrl = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/" + id;
        return this.restTemplate.getForObject(baseUrl, User.class);
    }
}

