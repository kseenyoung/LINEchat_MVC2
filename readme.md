
# LINE chat

web 환경에서 채팅을 즐겨보세요!


## Authors
<table>
  <tr>
         <td align="center" width="16%">
            <a href="https://github.com/kseenyoung"><img width="75%" src="readme/샐리.png"/></a>
            <br />
            <a href="https://github.com/kseenyoung">김신영</a>
        </td>
    </tr>


## Tech Stack

**Client:** Javascript, HTML, CSS

**Server:** Java, WebSocket, JSP

**Database:** Oracle



## Development Environment


`IDE` Intellij Altimate

`JDK` >= 21

`OS` mac



## Archetecture

<img src="readme/Architecture.png" width="75%"/>


## 디렉토리 구조

### src
``` text
src
 ┣ com
 ┃ ┣ shinhan
 ┃ ┃ ┣ auth
 ┃ ┃ ┃ ┣ model
 ┃ ┃ ┃ ┃ ┣ AuthDAO.java
 ┃ ┃ ┃ ┃ ┣ AuthService.java
 ┃ ┃ ┃ ┃ ┗ MemberDTO.java
 ┃ ┃ ┃ ┣ DeleteServlet.java
 ┃ ┃ ┃ ┣ LoginServlet.java
 ┃ ┃ ┃ ┣ SignUpServlet.java
 ┃ ┃ ┃ ┗ UpdateServlet.java
 ┃ ┃ ┣ chat
 ┃ ┃ ┃ ┣ model
 ┃ ┃ ┃ ┃ ┣ ChatDAO.java
 ┃ ┃ ┃ ┃ ┣ ChatDTO.java
 ┃ ┃ ┃ ┃ ┗ ChatServlet.java
 ┃ ┃ ┃ ┣ ChatClient.java
 ┃ ┃ ┃ ┣ ChatService.java
 ┃ ┃ ┃ ┗ WebSocket.java
 ┃ ┃ ┣ config
 ┃ ┃ ┃ ┗ HttpSessionConfigurator.java
 ┃ ┃ ┣ filter
 ┃ ┃ ┃ ┗ AuthFilter.java
 ┃ ┃ ┣ room
 ┃ ┃ ┃ ┣ model
 ┃ ┃ ┃ ┃ ┣ RoomDAO.java
 ┃ ┃ ┃ ┃ ┣ RoomDTO.java
 ┃ ┃ ┃ ┃ ┗ RoomService.java
 ┃ ┃ ┃ ┗ RoomListServlet.java
 ┃ ┃ ┣ util
 ┃ ┃ ┃ ┗ DBUtil.java
 ┃ ┃ ┗ .DS_Store
 ┃ ┗ .DS_Store
 ┗ .DS_Store
```

### web
``` text
web
 ┣ META-INF
 ┃ ┗ context.xml
 ┣ WEB-INF
 ┃ ┣ lib
 ┃ ┃ ┣ lombok.jar
 ┃ ┃ ┣ ojdbc11.jar
 ┃ ┃ ┣ taglibs-standard-compat-1.2.5.jar
 ┃ ┃ ┣ taglibs-standard-impl-1.2.5.jar
 ┃ ┃ ┣ taglibs-standard-jstlel-1.2.5.jar
 ┃ ┃ ┗ taglibs-standard-spec-1.2.5.jar
 ┃ ┗ web.xml
 ┣ auth
 ┃ ┣ css
 ┃ ┃ ┗ home.css
 ┃ ┣ js
 ┃ ┃ ┗ home.js
 ┃ ┣ delete.jsp
 ┃ ┗ home.jsp
 ┣ chat
 ┃ ┣ css
 ┃ ┃ ┗ chat.css
 ┃ ┗ chat.jsp
 ┣ error
 ┃ ┣ error404.jsp
 ┃ ┗ error500.jsp
 ┣ images
 ┃ ┣ line.png
 ┃ ┣ sky.gif
 ┃ ┗ sky.jpg
 ┣ jsp
 ┃ ┗ header.jsp
 ┣ resource
 ┃ ┗ LINEchat.sql
 ┣ room
 ┃ ┣ css
 ┃ ┃ ┗ rooms.css
 ┃ ┗ rooms.jsp
 ┗ .DS_Store
```

## API Reference

### Auth

#### login page

```http
  GET /auth/login.do
```

#### login

```http
  POST /auth/login.do
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `member_id` | `string` | **Required**. Id of member |
| `password`  | `string` | **Required**. password of member |

#### signup

```http
  POST /auth/signup.do
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `member_id` | `string` | **Required**. Id of member |
| `password`  | `string` | **Required**. password of member |

#### update nickname

```http
  POST /auth/update.do
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `modify_name` | `string` | **Required**. nickname for mofification |

#### delete

```http
  POST /auth/delete.do
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `password` | `string` | **Required**. password for delete member |


### Room

#### room page

```http
  GET /room/rooms.do
```

### Chat

#### chat page

```http
  GET /chat/chat.do?chatId
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `chatId` | `int` | **Required**. chatId for enter chat |

#### webSocket connect

```http
  WebSocket ws://ip:port/LINEchat/websocket?roomId
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `roomId` | `int` | **Required**. roomId for enter chat |



## Run Locally

Clone the project

```bash
  git clone https://link-to-project
```

Go to the project directory

```bash
  cd my-project
```

Install dependencies

```bash
  npm install
```

Start the server

```bash
  npm run start
```


## Screenshots

Login<br>
<img src="readme/Login.gif" width="75%"/>
<br>Signup<br>
<img src="readme/SignUp.gif" width="75%"/>
<br>Modify Nickname<br>
<img src="readme/ModifyNickName.gif" width="75%"/>
<br>Chatting<br>
<img src="readme/Chatting.gif" width="75%"/>
<br>Delete member<br>
<img src="readme/DeleteMember.gif" width="75%"/>
