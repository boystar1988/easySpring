package com.jianzhong.demo.websocket;

import com.jianzhong.demo.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
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
        addOnlineCount();
        log.info("打开连接. 当前人数：" + getOnlineCount());
        try {
            sendMessage("连接成功.session="+this.session.getId());
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        log.info("关闭连接. 当前人数：" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("【客户端】: " + message );
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误。", error);
    }

    public void sendMessage(String message) throws Exception {
        if (this.session.isOpen()) {
            this.session.getBasicRemote().sendText("收到信息:"+message);
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