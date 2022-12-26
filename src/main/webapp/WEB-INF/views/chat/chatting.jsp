<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="whole-page">
    <div class="left-page"></div>
    <div class="middle-page">
        <div class="up-page">
            <div class="page-title">
                MySNS오픈채팅방
            </div>
            <span class="title-sub">* 채팅방에 나가게 되면 모든 채팅 기록은 사라집니다.</span>
        </div>
        <div id='chat'>
            <input type="hidden" id="roomId" value="${chatroom.id}">

            <div class="room-info">
                <button class="room-btn">방이름</button>
                <div id="room-names">${chatroom.name}</div>
            </div>

            <div class="room-maker">
                <button class="room-btn">방장</button>
                <img class="image" src="/upload/${chatroom.user.profileImageUrl}" onerror="this.src='https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png'" />
                <div class="creator-name">${chatroom.user.name}</div>
            </div>

            <div id='talk'>
            </div>
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