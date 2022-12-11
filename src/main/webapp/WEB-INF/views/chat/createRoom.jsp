<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="whole-page">
    <div class="left-page"></div>
    <div class="middle-page">
        <form action="/createRoom" method="post">
            <div>방제목: <input name="room_name" type="text"></div>
            <div>제한인원: <input name="room_restrictNumber" type="text"></div>
            <button>생성</button>
        </form>

    </div>
    <div class="right-page"></div>
</div>
<div class="page-bottom"></div>