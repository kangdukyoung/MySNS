<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<META HTTP-EQUIV="refresh" CONTENT="10">
<div class="whole-page">
    <div class="left-page"></div>
    <div class="middle-page">
        <div class="makeRoom">
            <button class="makeRoom-btn"><a href="/createRoom">채팅방 만들기</a></button>
        </div>
        <br>
        <div id="room-list">
            <c:forEach var="chatroom" items="${chatrooms}">
                <div id="room">
                    <div id="room-title">
                        <div id="room-name">${chatroom.name}</div>
                        <c:choose>
                            <c:when test="${chatroom.user.id == principal.user.id}">
                                <button id="delete-room" onclick="deleteRoom(${chatroom.id})"><a href="/chatroom/delete/${chatroom.id}"><i class="fas fa-trash-alt fa-lg"></i></a></button>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div id="room-description">${chatroom.description}</div>

                    <div id="room-member">
                        <div class="one-line">
                            <span class="exist-number">현재 인원</span><p id="room-exist-${chatroom.id}">${chatroom.existNumber}</p>
                        </div>
                        <div class="two-line">
                            <span class="restrict-number">최대 인원</span><p id="room-restrict-${chatroom.id}">${chatroom.restrictNumber}</p>
                        </div>
                    </div>

                    <div id="creater-enterRoom">
                        <div id="creator-info">
                            <img class="image" src="/upload/${chatroom.user.profileImageUrl}" onerror="this.src='https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png'" />
                            <div id="room-creator" id="room-creator">${chatroom.user.name}</div>
                        </div>

                        <div class="enterRoom">
                            <button id="enterRoom" onclick="enterRoom(event,${chatroom.id});"><a href="/chatroom/${chatroom.id}/mychat/${principal.user.id}">지금 참여</a></button>
                        </div>
                    </div>
                    <%-- id값만 가져오기--%>
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