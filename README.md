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
