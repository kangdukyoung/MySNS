package com.cos.mysns.Service;


import com.cos.mysns.config.webSocket.ServerEndpointConfigurator;
import com.cos.mysns.domain.chatRoom.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
@ServerEndpoint(value="/chatroom/{roomId}/mychat/{userId}",configurator = ServerEndpointConfigurator.class)
public class ChatService {
    private static Set<Session> sessionSet = Collections.synchronizedSet(new HashSet<Session>());
    private static Map<Integer, ArrayList<Session>> sessionMap = Collections.synchronizedMap(new HashMap<Integer, ArrayList<Session>>());
    private final ChatRoomService chatRoomService;

    @OnOpen
    public void onOpen(Session s, @PathParam("roomId") int roomId, @PathParam("userId") Long userId ) {

        // 방 처음 들어왔으면 해당 방 세션리스트생성
        if(!sessionMap.containsKey(roomId)){
            sessionMap.put(roomId, new ArrayList<>());
        }

        //방에 아무도 없다면 해당 방 세션리스트생성 & 추가, 있다면 추가만
        if(sessionMap.get(roomId).size()==0){
            sessionMap.put(roomId, new ArrayList<>());
            sessionMap.get(roomId).add(s);
        }else{
            sessionMap.get(roomId).add(s);
        }

        if(!sessionSet.contains(s)) {
            sessionSet.add(s);
            log.info("세션 열림 : " + s);
        }else {
            log.info("이미 연결된 세션");
        }

        System.out.println("*******방마다 세션 객체 불러오기*******");
        for(Integer key: sessionMap.keySet()){
            log.info(key+"번방 : ");
            for(int j=0; j<sessionMap.get(key).size();j++){
                log.info(String.valueOf(sessionMap.get(key).get(j)));
            }
        }
    }

    @OnMessage
    public void onMessage(String msg, Session s) throws Exception{
        //사용자가 어느 방에 있는지 찾기
        Integer findkey = -1;
        for(int key: sessionMap.keySet()){
            for(int j=0; j<sessionMap.get(key).size();j++){
                if(sessionMap.get(key).get(j).equals(s)){
                    findkey = key;
                }
            }
        }
        // 방에 있는 세션 모두 tmpSessionSet에 저장
        Set<Session> tmpSessionSet = Collections.synchronizedSet(new HashSet<Session>());
        for(int j=0;j<sessionMap.get(findkey).size();j++){
            tmpSessionSet.add(sessionMap.get(findkey).get(j));
        }

        //같은 방에 있는 사람에게만 보낸다.
        for(Session session : tmpSessionSet) {
            log.info("전송 메세지 : " + msg);
            session.getBasicRemote().sendText(msg);
        }
        log.info("----------------------------");
    }


    @OnClose
    public void onClose(Session s) {
        log.info("세션 닫힘 : " + s);
        sessionSet.remove(s);

        Integer findkey = -1;
        for(int key: sessionMap.keySet()){
            for(int j=0; j<sessionMap.get(key).size();j++){
                if(sessionMap.get(key).get(j).equals(s)){
                    findkey = key;
                }
            }
        }
        sessionMap.get(findkey).remove(s);

        //방인원 -1
        ChatRoom chatRoom = chatRoomService.decreaseExist(findkey);

    }
}
