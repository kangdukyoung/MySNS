<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Photogram</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
</head>

<body>
    <div class="login">
        <div class="left-page">

        </div>
        <div class="middle-page">
            <div class="up-page"></div>
            <div class="login__form__container">
                <div class="login__form">
                    <div class="login-title">
                        <div class="login-text">로그인</div>
                        <div class="login-register">신규 회원이신가요?<span><a href="/auth/signup">새 계정 만들기</a></span></div>
                    </div>

                    <form class="login__input"  action="/auth/signin" method="POST">
                        <input type="text" name="username" placeholder="아이디" required="required" />
                        <input type="password" name="password" placeholder="비밀번호" />
                        <button>로그인</button>
                    </form>


                    <!-- Oauth 소셜로그인 -->
                    <div class="login__facebook">
                        <button onclick = "javascript:location.href='/oauth2/authorization/facebook'">
                            <i class="fab fa-facebook-square"></i>
                            <span>Facebook으로 로그인</span>
                        </button>
                        <div class="login__kakao">
                            <button onclick = "javascript:location.href='/oauth2/authorization/google'">
                                <i class="far fa-comment-dots"></i>
                                <span>Google로 로그인</span>
                            </button>
                        </div>
                        <!-- Oauth 소셜로그인end -->
                    </div>
                </div>

            </div>
        </div>

        <div class="right-page">

        </div>
    </div>

    <div class="page-bottom"></div>
</body>

</html>