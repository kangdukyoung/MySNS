package com.cos.photogramstart.Service;


import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@ServerEndpoint(value="/chat/{userId}")
public class ChatService {
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    //여기서 로그인시 세션생성해주면될듯
    @OnOpen
    public void onOpen(Session s, @PathParam("userId") String userId ) {
        if(!clients.contains(s)) {
            clients.add(s);
            System.out.println("session open : " + s);
        }else {
            System.out.println("이미 연결된 session 임!!!");
        }
    }


    @OnMessage
    public void onMessage(String msg, Session session) throws Exception{
        System.out.println("receive message : " + msg);
        for(Session s : clients) {
            System.out.println("send data : " + msg);
            s.getBasicRemote().sendText(msg);
        }
        System.out.println("----------------------------");
    }


    @OnClose
    public void onClose(Session s) {
        System.out.println("session close : " + s);
        clients.remove(s);
    }



}
