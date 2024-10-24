package com.cctegitc.ai.function.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.cctegitc.ai.function.modules.robotchat.service.DispatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

//import sun.misc.BASE64Encoder;
//import sun.misc.BASE64Decoder;

/**
 * 配置websocket
 */
@ServerEndpoint("/websocket")
@Component
@Slf4j
public class WebSocketServiceImpl {

    @Autowired
    private DispatchService dispatchService;

    @Autowired
    public void setDispatchService(DispatchService dispatch) {
        dispatchService = dispatch;
    }

    private static final AtomicInteger onlineCount = new AtomicInteger(0);

    private Session session;

    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSockets = new CopyOnWriteArraySet<>();

    private String sessionId = "";

    @OnOpen
    public void onOpen(Session session, @PathParam("sessionId") String sessionId) {
        this.session = session;
        this.sessionId = sessionId;
        webSockets.add(this);
        int count = onlineCount.incrementAndGet(); // 在线数加1
        log.info("有连接加入，当前连接数为:{}", count);
        sendMessage("连接成功");

    }

    /**
     * 关闭调用方法
     */
    @OnClose
    public void onClose() {
        webSockets.remove(this);
        int count = onlineCount.decrementAndGet();
        log.info("有连接关闭，当前连接数为:{}", count);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自客户 的信息: {}", sessionId, message);

        // 判断信息是否为空，这可以避免不必要的处理和潜在的错误
        if (message == null || message.isEmpty()) {
            log.warn("收到空消息，sessionId: {}", sessionId);
            return;
        }

        try {
            byte[] bytes = message.getBytes(); // 考虑指定字符集，如StandardCharsets.UTF_8

            // 检查数组是否为空更直接使用 message 是否为空或空串来判断
            ArrayList<byte[]> byteList = new ArrayList<>();
            byteList.add(bytes);
            String text = dispatchService.getText(byteList);
            log.info("处理结果：{}", text);
        } catch (Exception e) {
            log.error("处理来自 的消息时发生错误：", sessionId, e);
            // 根据错误情况决定是否需要关闭会话或通知客户端错误信息
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, "发生错误"));
            } catch (IOException ex) {
                log.error("尝试关闭会话时发生错误", ex);
            }
        }
    }


    // 将字符串转换成二进制字符串，以空格相隔
    private String StrToBinstr(String str) {
        char[] strChar = str.toCharArray();
        String result = "";
        for (int i = 0; i < strChar.length; i++) {
            result += Integer.toBinaryString(strChar[i]) + " ";
        }
        return result;
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) {
        try {
            //推送消息和当前socket通道的会话id (客户)
            this.session.getBasicRemote().sendText(message + "==" + sessionId);
        } catch (IOException e) {
            log.error("websocket异常: " + e.toString());
        }
    }

    /**
     * 实现点对点服务器主动推送
     */
    public static void sendInfo(String message, @PathParam("sessionId") String sessionId) {
        log.info("推送消息到窗口" + sessionId + "，推送内容:" + message);
        for (WebSocketServiceImpl item : webSockets) {
            //这里可以设定只推送给这个sid的，为null则全部推送
            if (sessionId == null) {
                item.sendMessage(message);
            } else if (item.sessionId.equals(sessionId)) {
                item.sendMessage(message);
            }
        }

    }

    /**
     * 实现服务器主动推送
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage1(String message) throws IOException {
        synchronized (session) {
            this.session.getBasicRemote().sendText(message);
        }
    }
}
