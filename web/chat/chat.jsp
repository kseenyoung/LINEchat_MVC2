<%--
  Created by IntelliJ IDEA.
  User: ksy
  Date: 11/22/24
  Time: 10:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Line chat</title>
    <link rel="icon" type="images/jpg" href="../images/line.png">

    <link rel="stylesheet" href="css/chat.css">
</head>
<body>
<main id="home-container">
    <h2>${room.name}</h2>
    <div id="main-container">
        <div id="chat-container">
        </div>
        <div id="bottom-container">
            <input id="inputMessage" type="text" onkeydown="handleEnter(event)">
            <input id="btn-submit" type="button" onclick="sendMessage()" value="전송">
        </div>
    </div>
    <script type="text/javascript">
        // 「WebSocketEx」는 프로젝트 명
        // 「websocket」는 호스트 명
        // WebSocket 오브젝트 생성 (자동으로 접속 시작한다. - onopen 함수 호출)
        var webSocket = new WebSocket("ws://192.168.0.19:9090/LINEchat/websocket?roomId=${roomId}");
        // 콘솔 텍스트 에리어 오브젝트
        // var messageTextArea = document.getElementById("messageTextArea");
        let chatContainer = document.getElementById("chat-container")
        // WebSocket 서버와 접속이 되면 호출되는 함수
        webSocket.onopen = function (message) {
            // 콘솔 텍스트에 메시지를 출력한다.
        };
        // WebSocket 서버와 접속이 끊기면 호출되는 함수
        webSocket.onclose = function (message) {
            // 콘솔 텍스트에 메시지를 출력한다.
        }
        ;
        // WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수
        webSocket.onerror = function (message) {
            // 콘솔 텍스트에 메시지를 출력한다.
        };
        // WebSocket 서버로 부터 메시지가 오면 호출되는 함수
        webSocket.onmessage = function (message) {
            // 콘솔 텍스트에 메시지를 출력한다.

            let dateInfo = new Date();
            let formattedDate =
                dateInfo.getFullYear() + "-" +
                String(dateInfo.getMonth() + 1).padStart(2, '0') + "-" +
                String(dateInfo.getDate()).padStart(2, '0') + " " +
                String(dateInfo.getHours()).padStart(2, '0') + ":" +
                String(dateInfo.getMinutes()).padStart(2, '0') + ":" +
                String(dateInfo.getSeconds()).padStart(2, '0');

            let $chat = '';
            if (message.data.startsWith("📢")) {
                $chat = "<div class='chat notice'>" + message.data + "</div>";
                // console.log(message.data)
            } else {
                let m = message.data.split(">")
                $chat = "<div class='chat-name chat-box' style='font-weight: bold'> " + m[0] + "</div><div class='chat-box'><div class='chat'>" + m[1] + "</div><div class='chat-info chat-box'>" + formattedDate + "</div></div>";
            }
            chatContainer.innerHTML += $chat;
            chatContainer.value += message.data + "\n";

            // 스크롤을 가장 아래로 이동
            chatContainer.scrollTop = chatContainer.scrollHeight;
        };

        // Send 버튼을 누르면 호출되는 함수
        function sendMessage() {
            // 송신 메시지를 작성하는 텍스트 박스 오브젝트를 취득한다.
            var message = document.getElementById("inputMessage");
            // 콘솔 텍스트에 메시지를 출력한다.
            <%--messageTextArea.value += "${member.name} > " + message.value + "\n";--%>
            let dateInfo = new Date();
            let formattedDate =
                dateInfo.getFullYear() + "-" +
                String(dateInfo.getMonth() + 1).padStart(2, '0') + "-" +
                String(dateInfo.getDate()).padStart(2, '0') + " " +
                String(dateInfo.getHours()).padStart(2, '0') + ":" +
                String(dateInfo.getMinutes()).padStart(2, '0') + ":" +
                String(dateInfo.getSeconds()).padStart(2, '0');

            let $chat = "<div class='send-box'><div class='chat'>" +
                message.value +
                "</div><div class='chat-info send-box'>" +
                formattedDate + "</div>" +
                "</div>";
            chatContainer.innerHTML += $chat;
            // WebSocket 서버에 메시지를 송신한다.
            webSocket.send(message.value);
            // 송신 메시지를 작성하는 텍스트 박스를 초기화한다.
            message.value = "";

            // 스크롤을 가장 아래로 이동
            chatContainer.scrollTop = chatContainer.scrollHeight;
        }    // Disconnect 버튼을 누르면 호출되는 함수
        function disconnect() {      // WebSocket 접속 해제
            webSocket.close();
        }

        function handleEnter(event) {
            if (event.key === "Enter") { // 엔터 키를 눌렀을 때
                sendMessage(); // sendMessage 함수 호출
                event.preventDefault(); // 폼 제출 등 기본 동작 방지
            }
        }
    </script>
</main>
</body>
</html>
