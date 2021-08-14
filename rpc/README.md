# Netty RPC Demo 实现
***

## 简介
&ensp;&ensp;&ensp;&ensp;一个 RPC 框架 Demo 的简单实现(业务场景中还没用过RPC，但Dome应该还是有那个意思的)

&ensp;&ensp;&ensp;&ensp;*完整的项目工程地址：[RPC-Demo](https://github.com/lw1243925457/JAVA-000/tree/main/homework/rpc/rpc-demoo)*

### 工程运行说明
- 服务端启动：rpcfx-demo-server：ServerApplication
- 客户端启动：rpcfx-demo-client：ClientApplication

### 工程结构说明

```bash
RPC-DEMO
├─rpcfx-core: 框架核心部分，client、server的代理
|
├─rpcfx-demo-api: 接口定义部分，类似于web后端给前端写的接口文档
|
├─rpcfx-demo-client: 客户端，调用接口发送请求
|
└─rpcfx-demo-server: 服务端，接收请求，进行CURD之类的
```

### 关于 RPC 的一些思考
&ensp;&ensp;&ensp;&ensp;秦老师说过一句话，可以道破其本质：RPC 就是为了 OOP。下面以HTTP的相似场景举例：

![](https://github.com/lw1243925457/JAVA-000/raw/main/homework/rpc/rpc-demo/flow1.png)

&ensp;&ensp;&ensp;&ensp;上面是一个比较简略的使用postman或者浏览器访问的数据流程，因为HTTP协议本质传输的也是一个问题。在自己接触的写前端请求后端接口，传输的基本是字符串

&ensp;&ensp;&ensp;&ensp;下面是 RPC 的：

![](https://github.com/lw1243925457/JAVA-000/raw/main/homework/rpc/rpc-demo/flow2.png)

&ensp;&ensp;&ensp;&ensp;RPC表示不想这么玩，咱后端就要有后端的样子，要像平时使用类，调用方法那样才用的爽。

&ensp;&ensp;&ensp;&ensp;类比平时Web的开发，客户端就像Vue前端，服务端就是使用Spring boot web写的那些业务CURD服务，RPC框架就是Spring Boot Web框架

&ensp;&ensp;&ensp;&ensp;RPC框架想达到的目的有如下几个：

- 1.客户端：封装屏蔽从客户端发送请求到服务端，并将结果序列号成对象的过程，让客户端感觉像在本地调用类方法一样
- 2.服务端：封装屏蔽服务端根据请求路由到基本的处理类和函数的查找，让服务端只专心写好CRUD即可

## 代码实现
&ensp;&ensp;&ensp;&ensp;根据上面的描述，客户端只需要调用接口获取结果进行处理，服务端只需要实现好接口CRUD就行了，其它的全由 RPC 框架帮你搞定

&ensp;&ensp;&ensp;&ensp;代码编写的大致流程如下：

- 1.定义接口：类似于后端给前端编写的 API 文档
- 2.客户端调用：客户端调用接口，获取结果，并反序列化成对象
- 3.服务端接收处理：服务端接收到请求，匹配相应的实现类，调用相应的方法，得到结果返回返回

### 1.定义接口：类似于后端给前端编写的 API 文档
&ensp;&ensp;&ensp;&ensp;类似于接口文档，描述了能提供哪些功能、能返回什么、需要传什么参数。即本工程的 API 模块

```java
public interface UserService {

    /**
     * find by id
     * @param id id
     * @return user
     */
    User findById(Integer id);
}
```

### 2.客户端调用：客户端调用接口，获取结果，并反序列成对象
&ensp;&ensp;&ensp;&ensp;客户端只需要简单的使用RPC生成的代理，进行调用即可

#### 2.1 客户端业务代码

```java
public class ClientApplication {

    public static void main(String[] args) {
        RpcClient jdk = new RpcClientJdk();
        UserService userService = jdk.create(UserService.class, "http://localhost:8080/");
        User user = userService.findById(1);
        if (user == null) {
            log.info("Clint service invoke Error");
            return;
        }
        System.out.println("find user id=1 from server: " + user.getName());
    }
}
```

#### 2.2 RPC 框架客户端：代理类生成、netty请求发送和响应处理
&ensp;&ensp;&ensp;&ensp;下面的发送请求和读取结果序列号返回都是 RPC 框架的事情了，大致代码如下：

&ensp;&ensp;&ensp;&ensp;生成代理类：

```java
public class RpcClientJdk extends RpcProxy implements RpcClient {

    @Override
    public <T> T create(Class<T> serviceClass, String url) {
        // 查询是否之前生成过，存储的直接返回
        if (!isExit(serviceClass.getName())) {
            add(serviceClass.getName(), newProxy(serviceClass, url));
        }
        return (T) getProxy(serviceClass.getName());
    }

    private <T> T newProxy(Class<T> serviceClass, String url) {
        ClassLoader loader = RpcClientJdk.class.getClassLoader();
        Class[] classes = new Class[]{serviceClass};
        return (T) Proxy.newProxyInstance(loader, classes, new RpcInvocationHandler(serviceClass, url));
    }
}
```

&ensp;&ensp;&ensp;&ensp;请求发送与返回处理具体实现

```java
/**
 * 用于jdk、cglib、buddy
 *
 * @author lw1243925457
 */
@Slf4j
public class RpcInvocationHandler implements InvocationHandler, MethodInterceptor {

    private final Class<?> serviceClass;
    private final String url;

    <T> RpcInvocationHandler(Class<T> serviceClass, String url) {
        this.serviceClass = serviceClass;
        this.url = url;
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        return process(serviceClass, method, args, url);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) {
        return process(serviceClass, method, args, url);
    }

    /**
     * 发送请求到服务端
     * 获取结果后序列号成对象，返回
     * @param service service name
     * @param method service method
     * @param params method params
     * @param url server host
     * @return object
     */
    private Object process(Class<?> service, Method method, Object[] params, String url) {
        log.info("Client proxy instance method invoke");

        // 自定义了Rpc请求的结构 RpcRequest,放入接口名称、方法名、参数
        log.info("Build Rpc request");
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setServiceClass(service.getName());
        rpcRequest.setMethod(method.getName());
        rpcRequest.setArgv(params);


        // 客户端使用的 netty，发送请求到服务端，拿到结果（自定义结构：rpcfxResponse)
        log.info("Client send request to Server");
        RpcResponse rpcResponse;
        try {
            rpcResponse = RpcNettyClientSync.getInstance().getResponse(rpcRequest, url);
        } catch (InterruptedException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

        log.info("Client receive response Object");
        assert rpcResponse != null;
        if (!rpcResponse.getStatus()) {
            log.info("Client receive exception");
            rpcResponse.getException().printStackTrace();
            return null;
        }

        // 序列化成对象返回
        log.info("Response:: " + rpcResponse.getResult());
        return JSON.parse(rpcResponse.getResult().toString());
    }
}

```

&ensp;&ensp;&ensp;&ensp;下面是 Netty 客户端部分，这部分的编写需要注意数据的流向和相应的处理：

&ensp;&ensp;&ensp;&ensp;下面是一些自定义的约定的数据库结构

```java
/**
 * Rpc 自定义请求结构
 * 
 * @author lw
 */
@Data
public class RpcRequest {

    /**
     * 接口类名称
     */
    private String serviceClass;
    
    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    private Object[] argv;
}
```

```java
/**
 * Rpc 自定义响应结果
 * @author lw
 */
@Data
public class RpcResponse {

    /**
     * 响应结果
     */
    private Object result;

    /**
     * 函数是否执行成功
     */
    private Boolean status;

    /**
     * 执行失败的异常信息
     */
    private Exception exception;
}
```

```java
/**
 * Netty 通信的数据格式
 * 
 * @author lw1243925457
 */
@Data
public class RpcProtocol {

    /**
     * 数据大小
     */
    private int len;

    /**
     * 数据内容
     */
    private byte[] content;
}
```

&ensp;&ensp;&ensp;&ensp;netty 通信处理需要注意的是数据形式的变化和流向的处理

&ensp;&ensp;&ensp;&ensp;客户端发送和接收的数据流向大致如下：

```bash
发送请求： RpcRequest --> bytes -> RpcProtocol -> bytes
接收请求： bytes -> RpcProtocol -> bytes -> RpcRequest
```

&ensp;&ensp;&ensp;&ensp;相关的处理函数如下：

&ensp;&ensp;&ensp;&ensp;RpcRequest --> bytes -> RpcProtocol

```java
public class RpcNettyClientSync {

    ......

    /**
     * 调用channel发送请求，从handler中获取响应结果
     * @return 响应
     * @throws InterruptedException exception
     */
    public RpcResponse getResponse(RpcRequest rpcRequest, String url) throws InterruptedException,
            URISyntaxException {
        RpcProtocol request = convertNettyRequest(rpcRequest);

        // 查看缓存池中是否有可重用的channel
        ......

        // 没有或者不可用则新建
        // 并将最终的handler添加到pipeline中，拿到结果后返回
        ......

        channel.writeAndFlush(request).sync();
        return handler.getResponse();
    }

    ......

    /**
     * 将 {@RpcRequest} 转成 netty 自定义的通信格式 {@RpcProtocol}
     * @param rpcRequest RpcRequest
     * @return RpcProtocol
     */
    private RpcProtocol convertNettyRequest(RpcRequest rpcRequest) {
        RpcProtocol request = new RpcProtocol();
        String requestJson = JSON.toJSONString(rpcRequest);
        request.setLen(requestJson.getBytes(CharsetUtil.UTF_8).length);
        request.setContent(requestJson.getBytes(CharsetUtil.UTF_8));
        return request;
    }
}
```

&ensp;&ensp;&ensp;&ensp;RpcProtocol -> bytes,到这请求就发送出去了

```java
/**
 * Rpc 自定义编码器
 * RpcProtocol -> bytes
 *
 * @author lw1243925457
 */
@Slf4j
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcProtocol msg, ByteBuf out) throws Exception {
        log.info("Netty rpc encode run");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
```

&ensp;&ensp;&ensp;&ensp;响应处理就是一个简单的逆向处理，在 netty中处理大致如下：

```java
/**
 * Rpc framework 自定义解码器
 * bytes -> rpcProtocol
 *
 * @author lw1243925457
 */
@Slf4j
public class RpcDecoder extends ByteToMessageDecoder {

    private int length = 0;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        log.info("Netty decode run");
        if (in.readableBytes() >= 4) {
            if (length == 0) {
                length = in.readInt();
            }
            if (in.readableBytes() < length) {
                log.info("Readable data is less, wait");
                return;
            }
            byte[] content = new byte[length];
            if (in.readableBytes() >= length) {
                in.readBytes(content);
                RpcProtocol rpcProtocol = new RpcProtocol();
                rpcProtocol.setLen(length);
                rpcProtocol.setContent(content);
                out.add(rpcProtocol);
            }
            length = 0;
        }
    }
}

/**
 * 这里使用并发的等待-通知机制来拿到结果
 * @author lw
 */
@Slf4j
public class RpcClientSyncHandler extends SimpleChannelInboundHandler<RpcProtocol> {

    private CountDownLatch latch;
    private RpcResponse response;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcProtocol msg) {
        log.info("Netty client receive message:");
        log.info("Message length: " + msg.getLen());
        log.info("Message content: " + new String(msg.getContent(), CharsetUtil.UTF_8));

        // 将 RpcResponse字符串 反序列化成 RpcResponse对象
        RpcResponse rpcfxResponse = JSON.parseObject(new String(msg.getContent(), CharsetUtil.UTF_8),
                RpcResponse.class);
        log.info("Netty client serializer : " + rpcfxResponse.toString());

        response = rpcfxResponse;
        latch.countDown();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 锁的初始化
     * @param latch CountDownLatch
     */
    void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 阻塞等待结果后返回
     * @return 后台服务器响应
     * @throws InterruptedException exception
     */
    RpcResponse getResponse() throws InterruptedException {
        latch.await();
        return response;
    }
}
```

&ensp;&ensp;&ensp;&ensp;这样，客户端的部分就写好了

### 3.服务端接收处理：服务端接收到请求，匹配相应的实现类，调用相应的方法，得到结果返回返回
&ensp;&ensp;&ensp;&ensp;服务端把 netty server 启动以后，专心写业务 CURD就行了；其他的部分都交给框架去处理

#### 3.1 服务端业务代码的编写
&ensp;&ensp;&ensp;&ensp;利用Spring，需要将接口相应的实现放入容器中，用于后面反射代理时查找获取，并启动 netty server。大致代码如下：

```java
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findById(Integer id) {
        return new Order(1, "RPC", 1);
    }

    @Override
    public Order findError() {
        throw new CustomException("Custom exception");
    }
}

/**
 * 配置接口的实现类
 * 
 * @author lw
 */
@Configuration
public class BeanConfig {

    @Bean("com.example.demo.service.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean("com.example.demo.service.OrderService")
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}

/**
 * 不使用 spring boot web，启动 netty server 进行监听
 * 
 * @author lw
 */
@SpringBootApplication
@Slf4j
public class ServerApplication implements ApplicationRunner {

    private final RpcNettyServer rpcNettyServer;

    public ServerApplication(RpcNettyServer rpcNettyServer) {
        this.rpcNettyServer = rpcNettyServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            rpcNettyServer.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rpcNettyServer.destroy();
        }
    }
}
```

#### 3.2 RPC 框架服务端：接收请求、生成代理类、调用方法、返回结果
&ensp;&ensp;&ensp;&ensp;服务端数据流向和客户相反，大致如下：

```bash
接收请求： bytes -> RpcProtocol -> bytes -> RpcRequest
发送请求： RpcRequest --> bytes -> RpcProtocol -> bytes
```

&ensp;&ensp;&ensp;&ensp;数据流向处理和客户端差不多，这里就不赘述了，大致代码如下：

```java
/**
 * Netty Server 启动类
 * 
 * @author lw1243925457
 */
@Slf4j
@Component
public class RpcNettyServer {

    private final ApplicationContext context;

    private EventLoopGroup boss;
    private EventLoopGroup worker;

    public RpcNettyServer(ApplicationContext context) {
        this.context = context;
    }

    public void destroy() {
        worker.shutdownGracefully();
        boss.shutdownGracefully();
    }

    public void run() throws Exception {
        boss = new NioEventLoopGroup(1);
        worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast("Message Encoder", new RpcEncoder());
                        pipeline.addLast("Message Decoder", new RpcDecoder()); // 将 byte 转为 RpcProtocol，放入下一个handler
                        pipeline.addLast("Message Handler", new RpcServerHandler(context));
                    }
                });

        int port = 8080;
        Channel channel = serverBootstrap.bind(port).sync().channel();
        log.info("Netty server listen in port: " + port);
        channel.closeFuture().sync();
    }
}

public class RpcServerHandler extends SimpleChannelInboundHandler<RpcProtocol> {

    private ApplicationContext applicationContext;

    RpcServerHandler(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcProtocol msg) throws Exception {
        log.info("Netty server receive message:");
        log.info("Message length: " + msg.getLen());
        log.info("Message content: " + new String(msg.getContent(), CharsetUtil.UTF_8));

        // 获取 RpcProtocol中的 RpcRequest内容，反序列化成 RpcRequest 对象
        RpcRequest rpcRequest = JSON.parseObject(new String(msg.getContent(), CharsetUtil.UTF_8),
                RpcRequest.class);
        log.info("Netty server serializer : " + rpcRequest.toString());

        // 获取相应的bean，反射调用方法，获取结果
        RpcResponse response = invoke(rpcRequest);

        // 返回结果给netty 客户端
        RpcProtocol message = new RpcProtocol();
        String requestJson = JSON.toJSONString(response);
        message.setLen(requestJson.getBytes(CharsetUtil.UTF_8).length);
        message.setContent(requestJson.getBytes(CharsetUtil.UTF_8));

        channelHandlerContext.writeAndFlush(message).sync();
        log.info("return response to client end");
    }

    /**
     * 获取接口实现对应的bean，反射调用方法，返回结果
     * @param request rpc request
     * @return result
     */
    private RpcResponse invoke(RpcRequest request) {
        RpcResponse response = new RpcResponse();
        String serviceClass = request.getServiceClass();

        Object service = applicationContext.getBean(serviceClass);

        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getArgv());
            log.info("Server method invoke result: " + result.toString());
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
            log.info("Server Response serialize to string return");
            return response;
        } catch ( IllegalAccessException | InvocationTargetException | CustomException e) {
            e.printStackTrace();
            response.setException(e);
            response.setStatus(false);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }
}
```

## 总结
&ensp;&ensp;&ensp;&ensp;到此，一个 Rpc Dome就搭建起来了，启动服务端，运行客户端就能看到运行结果。而且可以看到，复杂的部分都是框架帮忙处理的，客户端就像浏览器一样，根据接口直接调用就可以了。服务端也没有复杂的逻辑，写写CURD就好了。

## 错误记录处理
### Exception in thread "main" com.alibaba.fastjson.JSONException: autoType is not support. com.example.demo.model.User
- [Exception in thread "main" com.alibaba.fastjson.JSONException: autoType is not support. com.accord.f](Exception in thread "main" com.alibaba.fastjson.JSONException: autoType is not support. com.accord.f)

```java
// add this in client code
ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
```