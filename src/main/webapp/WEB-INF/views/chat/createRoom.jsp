<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="whole-page">
    <div class="left-page"></div>
    <div class="middle-page">
        <div class="up-page"></div>
        <div class="creating">
            <div class="create-title">
                오픈채팅방 설정
            </div>
            <div class="create-content">

                <form action="/createRoom" method="post">
                    <div>
                        <div>방제목</div>
                        <input name="room_name" type="text">
                    </div>

                    <div>
                        <div>방을 설명해주세요!</div>
                        <textarea name="room_description"></textarea>
                    </div>

                    <div>
                        <div class="restrictNumber">제한 인원<span class="restrict-comment">＊필수</span></div>
                        <input name="room_restrictNumber" type="number" min="1" max="100">
                    </div>

                    <button class="complete-btn">생성</button>
                </form>

            </div>
        </div>
    </div>
    <div class="right-page"></div>
</div>
<div class="page-bottom"></div>