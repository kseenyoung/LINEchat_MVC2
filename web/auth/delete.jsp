<%--
  Created by IntelliJ IDEA.
  User: ksy
  Date: 11/24/24
  Time: 11:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../jsp/header.jsp" %>
<%@ page errorPage="/error/error500.jsp" %>r
<html>
<head>
    <title>Line chat</title>
    <link rel="icon" type="images/jpg" href="../images/line.png">

    <link rel="stylesheet" href="css/home.css"/>
    <script>
        function f_delete(){
            alert("정말 탈퇴?");
        }
    </script>
</head>
<body>
<main id="home-container">
    <span onclick="location.href='${basePath}/room/rooms.do'" class="close"
          title="Close Modal">&times;</span>
    <form class="modal-content" action="../auth/delete.do" method="post" style="border-radius: 3%">
        <div class="container">
            <h1>회원탈퇴</h1>
            <hr>
            <label><b>비밀번호</b></label>
            <input type="text" placeholder="비밀번호 입력" name="modify_name" required>

            <p>${message}</p>
            <div class="clearfix">
                <button type="button" onclick="f_delete()" class="signupbtn">탈퇴</button>
            </div>
        </div>
    </form>
</main>
</body>
</html>
