package com.cos.photogramstart.Service;


import com.cos.photogramstart.domain.chatRoom.ChatRoom;
import com.cos.photogramstart.domain.chatRoom.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.*;


@Service
@ServerEndpoint(value="/chatroom/{roomId}/mychat/{userId}")
public class ChatService {
    private static Set<Session> sessionSet = Collections.synchronizedSet(new HashSet<Session>());
    private static Map<Integer, ArrayList<Session>> sessionMap = Collections.synchronizedMap(new HashMap<Integer, ArrayList<Session>>());


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
            System.out.println("session open : " + s);
        }else {
            System.out.println("이미 연결된 세션입니다.");
        }

        System.out.println("*******방마다 세션 객체 불러오기*******");
        for(Integer key: sessionMap.keySet()){
            System.out.print(key+"번방 : ");
            for(int j=0; j<sessionMap.get(key).size();j++){
                System.out.println(sessionMap.get(key).get(j));
            }
        }
        System.out.println();
    }

    @OnMessage
    public void onMessage(String msg, Session s) throws Exception{
        System.out.println("receive message : " + msg);

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
            System.out.println("send data : " + msg);
            session.getBasicRemote().sendText(msg);
        }
        System.out.println("----------------------------");
    }


    @OnClose
    public void onClose(Session s) {
        System.out.println("session close : " + s);
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


    }



}
