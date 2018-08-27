package cn.itcast.service.dao;

import cn.itcast.service.pojo.User;
import cn.itcast.service.pojo.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
//@DefaultProperties(defaultFallback = "queryUserByIdDefaultFallback")
public class UserDao {

   /* @Autowired
    private RestTemplate restTemplate;*/

   @Autowired
   private UserClient userClient;

    // eureka客户端，可以获取到eureka中服务的信息
    @Autowired
    private DiscoveryClient discoveryClient;

    //@HystrixCommand    //(fallbackMethod = "queryUserByIdDefaultFallback")
    public User queryUserById(Long id) {
        // 根据服务名称，获取服务实例。有可能是集群，所以是service实例集合
        //List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        // 因为只有一个UserService。所以获取第一个实例
        //ServiceInstance instance = instances.get(0);
        // 获取ip和端口信息，拼接成服务地址
        //String baseUrl = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/" + id;
        //return this.restTemplate.getForObject(baseUrl, User.class);


        //修改调用方法，不在手动获取ip和端口，而是直接通过服务名称调用
        //String baseUrl = "http://user-service/user/";
        User user = this.userClient.queryUserById(id);
        return user;
    }

   /* public User queryUserByIdDefaultFallback(){
        User user = new User();
        user.setUserName("默认提示：对不起，网络太拥挤了！");
        return user;

    }*/
}

