<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="whole-page2">
    <div class="left-page2"></div>
    <div class="middle-page2">
        <div class="up-page2"></div>

        <div class="search-box">
            <div class="search-header">
                <div class="search-title">친구를 검색하세요.</div>
                <input onkeyup="filter()" type="text" id="value" placeholder="이름을 검색하세요.">
            </div>

            <div class="search-container">
                <c:forEach var="user" items="${userList}">
                    <div class="item">
                        <div class="user-info">
                            <img class="icon" src="/upload/${user.profileImageUrl}" onerror="this.src='https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png'" >
                            <div class="name_div"><span class="name">${user.name}</span></div>
                        </div>
                        <div class="show-profile"><a href="/user/${user.id}">프로필 보기</a></div>
                    </div>

                </c:forEach>
            </div>
        </div>
    </div>
    <div class="right-page2"></div>
</div>
<div class="page-bottom2"></div>
<script src="/js/search.js"></script>
