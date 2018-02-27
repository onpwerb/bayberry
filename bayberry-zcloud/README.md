**微服务**

将一个原本独立的系统拆分成多个小型服务，这些小型服务在各自独立的进程中运行，服务之间基于HTTP的RESTful API进行通信协作。

**Spring Cloud**

一个基于Spring Boot实现的微服务架构开发工具。

**Spring Cloud 子项目列表**

- Spring Cloud Config 配置管理工具

- Spring Cloud Netflix 核心组件
    - Eureka 服务治理组件
    
        包含服务注册中心、服务注册与发现机制的实现
        
    - Hystrix 容错管理组件
    
        实现断路器模式，帮助服务依赖中出现的延迟和为故障提供强大的容错能力
        
    - Ribbon 客户端负载均衡的服务调用组件
        - Feign 基于Ribbon和Hystrix的声明式服务调用组件
        - Zuul 网关组件，提供智能路由、访问过滤等功能
        - Archaius 外部化配置组件
        
    - Spring Cloud Bus 事件、消息总线
    
        用于传播集群中的状态变化或事件，以触发后续的处理，比如用来动态刷新配置等
        
    - Spring Cloud Stream 
    
        通过Redis、Rabbit或者Kafka实现的消费微服务，可以通过简单的声明式模型来发送和接收消息
            
    - Spring Cloud Sleuth
    
        Spring Cloud应用的分布式跟踪实现，可以完美整合Zipkin    