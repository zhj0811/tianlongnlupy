package com.cctegitc.ai.function.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 配置websocket
 */
@ServerEndpoint("/websocket/{sid}")
@Component
@Slf4j
public class WebSocketServer1 {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer1> webSocketSet = new CopyOnWriteArraySet<WebSocketServer1>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //接收sid
    private String sid = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        //加入set中
        this.session = session;
        webSocketSet.add(this);
        //在线数加1
        addOnlineCount();
        log.info("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());
        this.sid = sid;
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从set中删除
        webSocketSet.remove(this);
        //在线数减1
        subOnlineCount();
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
//        log.info("收到来自窗口" + sid + "的信息:" + message);
//        //群发消息
//        for (WebSocketServer item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        synchronized (session) {
            this.session.getBasicRemote().sendText(message);
        }
    }

    /**
     * 点对点发送消息
     *
     * @param message
     * @param sid
     * @throws IOException
     */
    public void sendInfo1(String message, @PathParam("sid") String sid) {
        //log.info("推送消息到窗口" + sid + "，推送内容:"+ message);
        for (WebSocketServer1 ws : webSocketSet) {
            try {
                if (ws.sid.equals(sid)) {
                    ws.sendMessage(message);
                } else {
                    log.error("用户未在线，发送失败, sid为{}", sid);
                }
            } catch (IOException e) {
                log.error("发送失败", e);
            }
        }
    }

    /**
     * 传输二进制文件
     */
    public void sendBinary(ByteBuffer byteBuffer) throws IOException {
        synchronized (session) {
            this.session.getBasicRemote().sendBinary(byteBuffer);
        }

    }

    /**
     * 点对点发送二进制文件
     *
     * @param byteBuffer
     * @param sid
     * @throws IOException
     */
    public void sendInfo(ByteBuffer byteBuffer, @PathParam("sid") String sid) {
        log.info("推送消息到窗口" + sid + "，推送内容:" + byteBuffer);
        for (WebSocketServer1 ws : webSocketSet) {
            try {
                if (ws.sid.equals(sid)) {
                    ws.sendBinary(byteBuffer);
                } else {
                    log.error("用户未在线，发送失败, sid为{}", sid);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer1.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer1.onlineCount--;
    }
}
