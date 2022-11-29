<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<%@ include file="../layout/header.jsp" %>

<div class="whole-page">
    <div class="left-page"></div>
    <div class="middle-page">
        <div class="up-page"></div>

        <div class="upload">
            <div class="upload-top">
                SNS-project
            </div>
            <form class="upload-form" action="/image"  method="post" enctype="multipart/form-data">
                <div class="find-file">
                    <input class="upload-name" value="첨부파일" placeholder="첨부파일">
                    <label for="file">파일찾기</label>
                    <input type="file" name="file" id = "file" onchange="imageChoose(this)"/>
                </div>
                <div class="upload-img">
                    <img src="https://www.cmart.ca/Images/no_image.jpg" alt="" id="imageUploadPreview" />
                </div>
                <div class="upload-form-detail">
                    <textarea class="textarea-input" name="caption" placeholder="글을 입력해주세요..."></textarea>
                    <button class="upload-btn">업로드</button>
                </div>
            </form>
        </div>
    </div>
    <div class="right-page"></div>
</div>
<div class="page-bottom"></div>


<script src="/js/upload.js" ></script>