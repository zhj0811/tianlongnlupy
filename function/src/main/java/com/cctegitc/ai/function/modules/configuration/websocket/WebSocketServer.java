package com.cctegitc.ai.function.modules.configuration.websocket;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author a18700339798
 */
@ServerEndpoint(value = "/websocket/{userId}/{graphId}")
@Component
public class WebSocketServer {

    // 注入的时候，给类的 service 注入
    // 自定义中断标志
    private volatile boolean terminated = false;

    private final static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    // 新：使用map对象优化，便于根据sid来获取对应的WebSocket
    private static ConcurrentHashMap<String, WebSocketServer> websocketMap = new ConcurrentHashMap<>();
    // 接收用户的sid，指定需要推送的用户
    private String userId;
    // 组态id
    private String graphId;
    // 创建hashmap，用于存储线程
    private static ConcurrentHashMap<String, Thread> consoleThreadMap = new ConcurrentHashMap<String, Thread>();
    // 线程池
    private static ExecutorService threadPool;

    /**
     * 连接成功后调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId, @PathParam("graphId") String graphId) {
        this.session = session;
        //加入map中
        websocketMap.put(userId, this);
//        websocketMap.put(graphId, this);
        //在线数加1
        addOnlineCount();
        consoleThreadMap.put(userId, Thread.currentThread());
        //创建线程池
        threadPool = Executors.newFixedThreadPool(100);
        log.info("有新窗口开始监听:" + userId + ",当前在线人数为" + getOnlineCount());
        this.userId = userId;
        this.graphId = graphId;
//        try {
//            sendMessage("连接成功");
        threadPool.execute(new ClockThread(userId, graphId));
//        } catch (IOException e) {
//            log.error("websocket IO异常");
//        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (websocketMap.get(this.userId) != null) {
            // 从map中删除
            websocketMap.remove(this.userId);
            // 在线数减1
            subOnlineCount();
            log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
            terminated = true;
            consoleThreadMap.get(userId).interrupt();

        }
    }

    /**
     * 收到客户端消息后调用的方法，根据业务要求进行处理，这里就简单地将收到的消息直接群发推送出去
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + userId + "的信息:" + message);
        if (StringUtils.isNotBlank(message)) {
            for (WebSocketServer server : websocketMap.values()) {
                try {
                    if (!"连接成功".equals(message)) {
                        JSONObject jsonObject = JSONObject.parseObject(message);
                        // getParam(jsonObject.getString("anBiaoNumber"), jsonObject.getString("userId"));
                        // String enterpriseName = jsonObject.getString("enterpriseName");
                        // String productName = jsonObject.getString("productName");
                        // String model = jsonObject.getString("model");
                        getParam(jsonObject.getString("userId"), jsonObject.getString("graphId"));
                    } else {
                        server.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发生错误时的回调函数
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送消息
     */
    private void sendMessage(String message) throws IOException {
        synchronized (session) {
            System.out.println("session =" + session);
            this.session.getBasicRemote().sendText(message);
            String name = Thread.currentThread().getName();
            System.out.println("name=" + name);
        }
    }

    /**
     * 群发自定义消息（用set会方便些）
     */
    private static void sendInfo(String message) throws IOException {
        if (StringUtils.isNotBlank(message)) {
            for (WebSocketServer server : websocketMap.values()) {
                try {
                    // sid为null时群发，不为null则只发一个

                    server.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    private void getParam(String graphId, String userId) throws IOException {
        threadPool.execute(new ClockThread(graphId, userId));
    }

    class ClockThread implements Runnable {
        //String anBiaoNumber;
        String userId;
        String graphId;

        ClockThread(String userId, String graphId) {
            this.userId = userId;
            this.graphId = graphId;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (!terminated) {
            }
        }
    }
}
