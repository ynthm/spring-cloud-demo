# 说明

- `gateway` 网关服务，负责请求转发和鉴权功能，整合 Spring Security + Oauth2
- `auth` 认证服务，负责对登录用户进行认证，整合 Spring Security + Oauth2
- `Nacos` 作为注册中心，Gateway作为网关，使用`nimbus-jose-jwt`JWT库操作JWT令牌
- 实现RBAC模式的权限管理设计

最新版本的Spring Cloud已经放弃使用Ribbon来做负载均衡了，转而使用LoadBalancer

| 工程名       | 端口 | 描述               |
| ------------ | ---- | ------------------ |
| nacos-server | 8848 | 注册中心和配置中心 |
| gateway      | 8888 | API网关            |

## 只在 Gateway 做鉴权

## 服务与服务之间的权限

一般情况下，服务与服务之间可以直接调用，不需要加权限控制。但有些项目权限控制要求比较高，要求服务对服务之间的调用进行鉴权，知道某个用户是否有权限调用某个接口，这些都需要进行鉴权，这时的方案如下。

- 在Gateway网关层做认证，通过用户校验后，传递用户信息到Header中，后台服务在收到Header后进行解析，解析完后查看是否有调用此服务或者某个url的权限，然后完成鉴权。
- 从服务内部发出的请求，在出去时进行拦截，把用户信息保存在Header里，然后传出去,被调用方取到Header后进行解析和鉴权

## 注意事项

雪花算法生成的 `Long` 类型时，如果值超过了前端 `js` 显示的长度范围的话会导致数字精度丢失，但我们又不想变更字段的类型，此时我们可以在序列化返回时将 `Long` 类型转换成 `String` 类型。

## IDEA @Autowired 报错

使用 Mybatis 框架，而大家在 Service 层引入自己编写的 Mapper 接口时应该会遇到。

因为 @Mapper 这个注解是 Mybatis 提供的，而 @Autowried 注解是 Spring 提供的，IDEA能理解 Spring 的上下文，但是却和 Mybatis 关联不上。而且我们可以根据 @Autowried
源码看到，默认情况下，@Autowried 要求依赖对象必须存在，那么此时 IDEA 只能给个红色警告了。

简单粗暴，给@Autowried注解设置required=false

```java
@Autowired(required = false)
private CarUserMapper carUserMapper;
```

@Autowried替换为@Resource

```java
@Resource
private CarUserMapper carUserMapper;
```

在Mapper接口上加@Component

```java
@Mapper
@Component
public inteface CarUserMapper{}
```

加这个注解呢，主要是为了让欺骗 IEDA，让它以为CarUserMapper也是一个Spring管理的Bean，这样子使用@Autowired注解注入也不会报错了。

## docker redis

```sh
# \\wsl$\docker-desktop-data\data\docker\volumes\redis-vol\_data
docker volume create redis-vol
docker volume inspect redis-vol
docker run -d \
  --name my-redis \
  -p 6379:6379 \
  -v redis-vol:/data \
  redis:latest
  
docker volume create mysql-vol 
docker run -d --name my-mysql \
-e MYSQL_ROOT_PASSWORD=wang0804 \
-p 3306:3306 \
-v mysql-vol:/var/lib/mysql \
mysql:latest
```

