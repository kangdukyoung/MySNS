<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="whole-page">
    <div class="left-page"></div>
    <div class="middle-page">
        <div id='chat'>
            <input type="hidden" id="roomId" value="${chatroom.id}">
            <div id="room-name">${chatroom.name}</div>
            <div id="room-creator">${chatroom.creator}</div>
            <div id='talk'></div>
            <div id='sendZone'>
                <textarea id='msg' value='hi...' ></textarea>
                <input type='button' value='전송' id='btnSend'>
            </div>
        </div>

    </div>
    <div class="right-page"></div>
</div>
<div class="page-bottom"></div>

<script src='/js/chat.js'></script>