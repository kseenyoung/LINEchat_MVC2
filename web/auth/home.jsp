<%--
  Created by IntelliJ IDEA.
  User: ksy
  Date: 11/18/24
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../jsp/header.jsp" %>
<html>
<head>
    <link rel="icon" type="images/jpg" href="../images/line.png">
    <link rel="stylesheet" href="${basePath}/auth/css/home.css"/>
    <link rel="stylesheet" href="${basePath}/auth/js/home.js"/>
    <script>
        $(function () {
            // 페이지 로드 시 Remember me 상태에 따라 ID를 불러옵니다.
            const savedMemberId = localStorage.getItem("rememberedMemberId") || ""; // 저장된 아이디 불러오기
            const rememberChecked = localStorage.getItem("rememberChecked") === "true";

            $("#member_id").val(savedMemberId); // 저장된 아이디를 입력 필드에 설정
            $("#remember").prop("checked", rememberChecked); // 체크박스 상태 설정

            // 로그인 버튼 클릭 이벤트
            $("form").on("submit", function (event) {
                // Remember me 체크박스 상태 확인
                const remember = $("#remember").is(":checked");
                const memberId = $("#member_id").val();

                if (remember) {
                    // Remember me 체크 상태라면 ID 저장
                    localStorage.setItem("rememberedMemberId", memberId);
                    localStorage.setItem("rememberChecked", "true");
                } else {
                    // Remember me 체크 해제 상태라면 저장된 ID 제거
                    localStorage.removeItem("rememberedMemberId");
                    localStorage.removeItem("rememberChecked");
                }
            });
        });
    </script>
</head>
<body>

<div id="home-container">
    <div id="title">
    <img src="../images/line.png" alt="Avatar Logo" style="width:40px; margin: 10px">
    <h1>LINE chat</h1></div>
    <div class="container">
        <form action="login.do" method="post" class="animate">

            <div class="container">
                <label for="uname"><b>아이디</b></label>
                <input id="member_id" type="text" placeholder="Enter id" name="member_id" required>

                <label for="psw"><b>비밀번호</b></label>
                <input type="password" placeholder="Enter Password" name="member_password" required>

                <p style="color: red; font-size: 10px">${message}</p>
                <button type="submit">Login</button>
                <label>
                    <input id="remember" type="checkbox" name="remember"> Remember Id
                </label>
            </div>

            <%--    <button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Login</button>--%>
    </div>
    <div class="container">
        <h3>회원가입</h3>

        <button onclick="document.getElementById('id02').style.display='block'" style="width:auto;">Sign Up</button>
    </div>

    <p style="color: #04AA6D">${signup_message}</p>
</div>
<div id="id01" class="modal">


    <div class="container" style="background-color:#f1f1f1">
        <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">
            Cancel
        </button>
    </div>
    </form>
</div>

<div id="id02" class="modal">
        <span onclick="document.getElementById('id02').style.display='none'" class="close"
              title="Close Modal">&times;</span>
    <form class="modal-content" action="signup.do" method="post">
        <div class="container">
            <h1>회원가입</h1>
            <hr>
            <label><b>아이디</b></label>
            <input type="text" placeholder="Enter Email" name="member_id" required>

            <label><b>비밀번호</b></label>
            <input type="password" placeholder="Enter Password" name="member_password" required>

            <label><b>닉네임</b></label>
            <input type="text" placeholder="Repeat Password" name="member_name" required>

            <div class="clearfix">
                <button type="button" onclick="document.getElementById('id02').style.display='none'"
                        class="cancelbtn">Cancel
                </button>
                <button type="submit" class="signupbtn">회원가입</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
