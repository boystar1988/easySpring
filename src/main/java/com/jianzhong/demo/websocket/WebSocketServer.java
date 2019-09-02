package com.jianzhong.demo.websocket;

import com.jianzhong.demo.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/ws/{asset_type}/{foreign_id}/{token}")
@RestController
@Slf4j
public class WebSocketServer {

    @Autowired
    AuthService authService;

    // 用来记录当前连接数的变量
    private static volatile int onlineCount = 0;

    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    // 与某个客户端的连接会话，需要通过它来与客户端进行数据收发
    private Session session;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config,@PathParam("asset_type") String assetType, @PathParam("foreign_id") long foreignId, @PathParam("token") String token) throws Exception {
        this.session = session;
        webSocketSet.add(this);
        String sessionid = String.valueOf(config.getUserProperties().get("sessionid"));
        log.info("打开连接. assetType={}, foreignId={}, token={},session={}", assetType, foreignId, token,sessionid);
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("关闭连接. "+this.session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自客户端的消息: " + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误。", error);
    }

    public void sendMessage(String message) throws Exception {
        if (this.session.isOpen()) {
            this.session.getBasicRemote().sendText("收到信息. ");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}