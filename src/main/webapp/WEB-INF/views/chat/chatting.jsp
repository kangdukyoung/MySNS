<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div id='chatt'>
    <h1>WebSocket Chatting</h1>
    <input type='text' id='mid' value='홍길동'>
    <input type='button' value='로그인' id='btnLogin'>
    <br/>
    <div id='talk'></div>
    <div id='sendZone'>
        <textarea id='msg' value='hi...' ></textarea>
        <input type='button' value='전송' id='btnSend'>
    </div>
</div>
<script src='/js/chat.js'></script>