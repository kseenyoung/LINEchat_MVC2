<%--
  Created by IntelliJ IDEA.
  User: ksy
  Date: 11/19/24
  Time: 5:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../jsp/header.jsp" %>
<html>
<head>
    <title>Line chat</title>
    <link rel="icon" type="images/jpg" href="../images/line.png">

    <link rel="stylesheet" href="${basePath}/room/css/rooms.css"/>
    <style>
        #menu {
            background-color: #02b902;
            border-color: #02b902;
        }
    </style>
    <script>
        function f_delete() {
            let msg = window.prompt("회원탈퇴하시겠습니까? 비밀번호를 입력해주세요");
            let input = confirm('정말 탈퇴하시겠습니까?');
            if(!input){
                return;
            }

            if (msg != null) {
                $.ajax({
                    url: "${basePath}/auth/delete.do",
                    data: {password: msg},
                    type: 'post',
                    success: function (data) {
                        // console.log(data)
                        if(data=="true"){
                            location.href="${basePath}/auth/login.do"
                        } else{
                            $("#message").html("<p style='font-size: 10px; color: red' '>비밀번호가 틀렸습니다.</p>")
                        }
                    },
                    error: function () {
                    }
                })
            }
        }
    </script>
</head>
<body style="background: url(../images/sky.jpg) center; background-size: cover;">
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" id="logo" href="${basePath}/auth/login.do">
            <img src="../images/line.png" alt="Avatar Logo" style="width:40px;">
            <b style="color: #02b902">LINE chat</b>
        </a>
        <li class="nav-item dropdown toggle">
            <div>
                <span id="message"></span>
                <span><b>${member.name}</b>님 </span>
            </div>
            <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown">마이 페이지</a>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" onclick="document.getElementById('id02').style.display='block'">닉네임
                    변경하기</a></li>
                <li><a class="dropdown-item" onclick="f_delete()">회원 탈퇴</a>
                </li>
            </ul>
        </li>
    </div>
</nav>
<!-- 닉네임 변경 -->
<div id="id02" class="modal">
        <span onclick="document.getElementById('id02').style.display='none'" class="close"
              title="Close Modal">&times;</span>
    <form class="modal-content" action="../auth/update.do" method="post">
        <div class="container">
            <h1>닉네임 변경</h1>
            <hr>
            <label><b>변경할 닉네임</b></label>
            <input type="text" placeholder="닉네임 입력" name="modify_name" required>

            <div class="clearfix">
                <button type="button" onclick="document.getElementById('id02').style.display='none'"
                        class="cancelbtn">Cancel
                </button>
                <button type="submit" class="signupbtn">변경</button>
            </div>
        </div>
    </form>
</div>
<div>
    <main id="home-container">
        <div class="list-group">
            <a class="list-group-item list-group-item-action active" id="menu">
                Chat List
            </a>
            <c:forEach items="${rooms}" var="room">
                <a href="${basePath}/chat/chat.do?chatId=${room.roomId}"
                   class="list-group-item list-group-item-action">${room.name}</a>
            </c:forEach>
        </div>
    </main>
</div>
</body>
</html>
