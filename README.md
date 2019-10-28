# easySpring

## 目录结构
```
- document                          文档
- src
    -main
        -java
            -com.jianzhong.demo     包
                -config             配置
                -constant           常量
                -controller         控制器
                -domain             实体
                -event              事件
                -filter             过滤器
                -jobs               队列任务
                -listener           监听
                -repository         数据接口层
                -security           安全
                -service            业务层
                -utils              工具
                -vo                 输出模型
                -websocket          WebSocket
        -resources
            -logs                   日志
            -mybatis                mybatis
                -config             mybatis配置
                -mapping            元数据
            -static                 静态资源
            -template               模板
            -application.yml        应用参数
            -application-dev.yml    应用参数开发
            -application-prod.yml   应用参数正式
    -test                           单元测试
-target                             目标
    
```

## Spring Cloud兼容
|Spring Cloud| Spring Boot|
|------|------|
|Finchley|兼容Spring Boot 2.0.x，不兼容Spring Boot 1.5.x|
|Dalston和Edgware|兼容Spring Boot 1.5.x，不兼容Spring Boot 2.0.x|
|Camden|兼容Spring Boot 1.4.x，也兼容Spring Boot 1.5.x|
|Brixton|兼容Spring Boot 1.3.x，也兼容Spring Boot 1.4.x|
|Angel|兼容Spring Boot 1.2.x|



## 插件(plugin)整合
maven
    mybatis-generator
    docker