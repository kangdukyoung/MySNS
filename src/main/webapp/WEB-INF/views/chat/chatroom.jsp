<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="whole-page">
    <div class="left-page"></div>
    <div class="middle-page">
        <div class="makeRoom">
            <button><a href="/createRoom">채팅방 만들기</a></button>
        </div>
        <br>
        <div id="room-list">
            <c:forEach var="chatroom" items="${chatrooms}">
                <div id="room">
                    <div id="room-name">${chatroom.name}</div>
                    <div id="room-creator">${chatroom.creator}</div>
                    현재인원: <div id="room-exist">${chatroom.existNumber}</div>
                    제한인원: <div id="room-restrict">${chatroom.restrictNumber}</div>

                    <button id="enterRoom"><a href="/chatroom/${chatroom.id}/mychat/${principal.user.id}">입장</a></button>
                    <input type="hidden" id="roomId" value="${chatroom.id}">
                </div>
                <br>
            </c:forEach>
        </div>


    </div>
    <div class="right-page"></div>

</div>
<div class="page-bottom"></div>
<script src="/js/chatroom.js"></script>