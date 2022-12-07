<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="whole-page">
    <div class="left-page"></div>
    <div class="middle-page">
        <div class="makeRoom">
            <button onclick="createRoom()">채팅방 만들기</button>
        </div>
        <br>
        <div class="room-list">
            <div class="room">
                <div id="room-name">세번째 방</div>
                <div id="room-creator">윤예지</div>
                <button onclick="enterRoom()">입장</button>
            </div>
            <br>

        </div>


    </div>
    <div class="right-page"></div>

</div>
<div class="page-bottom"></div>
<script src="/js/chatroom.js"></script>
