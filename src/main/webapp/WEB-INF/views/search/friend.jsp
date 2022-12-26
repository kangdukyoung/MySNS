<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="searchbox">
    <div class="search-header">
        <div class="search-title">친구를 검색하세요.</div>
        <input onkeyup="filter()" type="text" id="value" placeholder="이름을 검색하세요.">
    </div>

    <div class="search-container">
        <c:forEach var="user" items="${userList}">
            <div class="item">
                <div class="user-info">
                    <img class="icon" src="/upload/${user.profileImageUrl}" onerror="this.src='https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png'" >
                    <span class="name">${user.name}</span>
                </div>
                <div class="show-profile"><a href="/user/${user.id}">프로필 보기</a></div>
            </div>
            <hr>
        </c:forEach>
    </div>

</div>
<script src="/js/search.js"></script>


<%--<div class="whole-page">--%>
<%--    <div class="left-page"></div>--%>
<%--    <div class="middle-page">--%>
<%--        <div class="up-page"></div>--%>

<%--        <div class="friend-content">--%>
<%--            여기서부터 친구목록--%>

<%--        </div>--%>


<%--    </div>--%>
<%--    <div class="right-page"></div>--%>

<%--</div>--%>
<%--<div class="page-bottom"></div>--%>

