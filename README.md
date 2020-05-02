这是一个跟着B站视频学习Spring Cloud的代码库。

1. 在maven父工程下面，启动payment和order两个子工程服务，并添加热部署;
2. 将两个服务中公共的部分抽成common模块，别的服务引用他;

Eureka注册中心篇:
1. 添加服务注册中心Eureka集群; 保留自我保护机制
2. 添加payment作为集群，并注册到Eureka上; 
    - 添加服务id信息
    - enable discovery client
