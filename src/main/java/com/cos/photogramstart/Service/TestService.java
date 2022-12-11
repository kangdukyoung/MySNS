package com.cos.photogramstart.Service;

import jdk.jshell.spi.ExecutionControl;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestService {
    public static void main(String[] args){
        Map<Integer, ArrayList<String>> sessionMap = Collections.synchronizedMap(new HashMap<Integer, ArrayList<String>>());

        sessionMap.put(1, new ArrayList<>());
        sessionMap.get(1).add("kangdukyoung");
        sessionMap.get(1).add("yoonyeji");

        sessionMap.put(2, new ArrayList<>());
        sessionMap.get(2).add("amumu");




        for(Integer key: sessionMap.keySet()){
            System.out.println(key);
            for(int j=0; j<sessionMap.get(key).size();j++){
                System.out.println(sessionMap.get(key).get(j));
            }
        }
        System.out.println("*********sangho 추가*********");
        if(sessionMap.get(1).size()==0){
            sessionMap.put(1, new ArrayList<>());
            sessionMap.get(1).add("sangho");
        }else{
            sessionMap.get(1).add("sangho");
        }

        for(Integer key: sessionMap.keySet()){
            System.out.println(key);
            for(int j=0; j<sessionMap.get(key).size();j++){
                System.out.println(sessionMap.get(key).get(j));
            }
        }

        System.out.println("*******1번방 다 없애고 minkyu 생성*******");
        sessionMap.get(1).remove("kangdukyoung");
        sessionMap.get(1).remove("yoonyeji");
        sessionMap.get(1).remove("sangho");

        if(sessionMap.get(1).size()==0){
            sessionMap.put(1, new ArrayList<>());
            sessionMap.get(1).add("minkyu");
        }else{
            sessionMap.get(1).add("minkyu");
        }

        for(Integer key: sessionMap.keySet()){
            System.out.println(key);
            for(int j=0; j<sessionMap.get(key).size();j++){
                System.out.println(sessionMap.get(key).get(j));
            }
        }

        System.out.println("*******3번방 add*******");



//        System.out.println("*****For 문*****");
//        for(String key : map.keySet()) {
//            String value = (String) map.get(key);
//            System.out.println(key + " : " + value);
//        }

    }
}
