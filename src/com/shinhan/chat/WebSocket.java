package com.shinhan.chat;

import com.shinhan.auth.model.MemberDTO;
import com.shinhan.chat.model.ChatDTO;
import com.shinhan.config.HttpSessionConfigurator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket", configurator = HttpSessionConfigurator.class)
public class WebSocket {
    // 방별로 세션을 관리하기 위한 ConcurrentHashMap
    private static final Map<String, Set<Session>> roomSessions = new ConcurrentHashMap<>();
    private static final Map<Session, String> sessionRooms = new ConcurrentHashMap<>();
    // 특정 사용자 정보 관리 위한 ConcurrentHashMap
    private static final Map<Session, MemberDTO> sessionUsers = new ConcurrentHashMap<>();

    ChatService chatService = new ChatService();


    // WebSocket으로 브라우저가 접속하면 요청되는 함수
    @OnOpen
    public void handleOpen(Session session, EndpointConfig config) {
        // WebSocket URL에서 쿼리 파라미터 가져오기
        String query = session.getQueryString(); // e.g., "roomId=12345&userToken=abcde"
        String[] keyValue = null;
        System.out.println("query : " + query);
        if (query == null || !query.contains("roomId=")) {
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "Invalid roomId"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        String roomId = query.split("=")[1];

        // 방 ID를 기반으로 세션 추가
        roomSessions.computeIfAbsent(roomId, k -> ConcurrentHashMap.newKeySet()).add(session);
        sessionRooms.put(session, roomId);

        // HttpSession에서 사용자 정보 가져오기
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSessionConfigurator.Session);
        MemberDTO member = (MemberDTO) httpSession.getAttribute("member"); // 사용자 정보 가져오기
        if (member == null) {
            member = MemberDTO.builder().name("Unknown").build();
        }

        sessionUsers.put(session, member);
        // 현재 방의 인원수 계산
        int currentUsers = roomSessions.get(roomId).size();

        // 입장 알림 메시지 생성
        String joinMessage = "📢 " + member.getName() + "님이 입장하셨습니다. 현재 인원: " + currentUsers + "명";

        // 브로드캐스트: 입장한 사람 제외
        broadcastToRoom(roomId, joinMessage, session);
    }


    // WebSocket으로 메시지가 오면 요청되는 함수
    @OnMessage
    public void handleMessage(String message, Session session) {
        String roomId = sessionRooms.get(session);
        if (roomId == null) {
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "No room associated with session"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        String userName = sessionUsers.get(session).getName(); // 보낸 사람 이름
        System.out.println("[" + userName + "][" + roomId + "] : " + message);

        // DB에 저장
        ChatDTO chat = ChatDTO.builder()
                .room_id(Integer.parseInt(roomId))
                .member_id(sessionUsers.get(session).getMemberId())
                .content(message)
                .build();
        chatService.insertMessage(chat);

        // 같은 방의 모든 사용자에게 메시지 브로드캐스트
        broadcastToRoom(roomId, userName + " > " + message, session);
    }

    // WebSocket과 브라우저가 접속이 끊기면 요청되는 함수
    @OnClose
    public void handleClose(Session session) {
        String roomId = sessionRooms.remove(session);
        Set<Session> sessions = null;
        MemberDTO member = sessionUsers.remove(session); // 퇴장한 사용자 정보 가져오기

        if (roomId != null) {
            sessions = roomSessions.get(roomId);
            if (sessions != null) {
                sessions.remove(session);
                if (sessions.isEmpty()) {
                    roomSessions.remove(roomId);
                }
            }
        }

        // 현재 방의 인원수 계산
        int currentUsers = sessions == null ? 0 : sessions.size();

        // 퇴장 알림 메시지 생성
        String leaveMessage = "📢 " + (member != null ? member.getName() : "알 수 없는 사용자") +
                "님이 퇴장하셨습니다. 현재 인원: " + currentUsers + "명";

        System.out.println("Client disconnected from room: " + roomId);

        // 브로드캐스트: 퇴장 알림
        broadcastToRoom(roomId, leaveMessage, null); // null로 송신자 없이 브로드캐스트
    }

    // WebSocket과 브라우저 간에 통신 에러가 발생하면 요청되는 함수.
    @OnError
    public void handleError(Throwable t) {
        // 콘솔에 에러를 표시한다.
        t.printStackTrace();
    }

    // 특정 방에 메시지 브로드캐스트
    private void broadcastToRoom(String roomId, String message, Session sender) {
        Set<Session> sessions = roomSessions.get(roomId);
        if (sessions != null) {
            for (Session session : sessions) {
                if (!session.equals(sender)) { // 송신자는 제외
                    try {
                        session.getBasicRemote().sendText(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
