这是一个跟着B站视频学习Spring Cloud的代码库。

1. 在maven父工程下面，启动payment和order两个子工程服务，并添加**热部署**;
2. 将两个服务中**公共的部分抽成common模块**，别的服务引用他;

[Eureka](https://github.com/Netflix/eureka)注册中心篇:
1. 添加服务注册中心Eureka集群; 保留自我保护机制
2. 添加payment作为集群，并注册到Eureka上; 
    - 添加服务id信息
    - enable discovery client

[Zookeeper](https://github.com/apache/zookeeper) & [Consul](https://www.consul.io/) 使用起来配置都十分类似，不过需要外部启动对应的服务。
payment8004是一个使用zookeeper的demo，可以看到对应的配置。
- Eureka主要考虑AP
- Zookeeper和Consul主要考虑CP


[Ribbon](https://github.com/Netflix/ribbon):
是什么？
- 负载均衡的工具。主要功能是提供客户端的负载均衡算法和服务调用
- 在配置文件中列出LB后面所有的Instance，可以帮助基于某种规则(如简单轮询，随机连接等)去连接这些Instance
- 可以根据Ribbon实现自定义负载均衡算法
Eureka Client自带Ribbon

工作步骤:
1. 选择Eureka Server，查询可用的服务列表；
2. 根据用户指定的策略，从取到的服务列表中选择一个地址；

核心组件IRule:  
根据特定的算法从服务列表中选取一个要访问的服务。默认带有随机、轮询、故障少、响应快等等规则，可以去看源码里面对应IRule接口的实现类。

自己实现IRule替换：（**不要放在SpringBoot App可以扫描到的地方**）

[OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
Feign是一个声明式的web服务客户端，让编写web服务更加的容易，只需要添加一个接口并在接口上添加注解即可。
通常而言，微服务间的服务调用使用RestTemplate。Feign在此基础上做了进一步封装，让调用微服务跟调用方法一样简单顺滑。(RPC框架)

OpenFeign在Feign的基础上，支持了SpringMVC 的注解。可以解析@RequestMapping下的接口，动态代理的方式产生实现类。
Tips:
1. Feign默认的超时时间是1s, 如果需要处理的话，需要添加额外的配置;
2. Feign的日志级别:
    - None: 默认，不记录日志
    - BASIC: 仅记录请求方法、URL、响应状态码以及执行时间
    - HEADERS: BASIC + req/res header信息
    - FULL: 全纪录

[Hystrix](https://github.com/Netflix/Hystrix)
Latency and Fault Tolerance for Distributed Systems. 避免系统的级联故障，提高分布式系统的弹性。
服务出现问题时候，给调用者返回一个符合预期的、可处理的备选响应（FallBack），而不是长时间的等待或者抛出调用方无法处理的异常。

服务调用的问题有两种:
1. 延迟
2. 错误

解决的方向有两个:
1. 服务端
2. 客户端
