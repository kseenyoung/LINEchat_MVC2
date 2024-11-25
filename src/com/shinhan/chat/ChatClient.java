//package com.shinhan.chat;
//
//package main;
//
////import com.fasterxml.jackson.core.type.TypeReference;
////import com.fasterxml.jackson.databind.JsonMappingException;
////import com.fasterxml.jackson.databind.ObjectMapper;
////import com.google.gson.JsonParseException;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.EOFException;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.Socket;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.NoSuchElementException;
//import java.util.Objects;
//import java.util.StringTokenizer;
//
//
//public class ChatClient {
//    //필드
//    Socket socket;
//    static ChatClient chatClient;
//    DataInputStream dis;
//    DataOutputStream dos;
//    String chatName;
//    int roomId;
//    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    static StringBuilder sb;
//    StringTokenizer st;
////    Map<Integer, RoomDTO> rooms = new HashMap<>();
//    String page = "home";  // TODO Enum 작성, home/ room/ chat
//    String id;
//    // receive Thread의 안전한 종료를 위한 filed
//    private volatile boolean running = true;
//    private volatile boolean disconnected = false; // 서버 연결 종료 상태 플래그
//
//
//    //메소드: 서버 연결
//    public void connect() throws IOException {
//        String ip = "192.168.0.25";
////        String ip2 = "192.168.0.4";
//        String ip3 = "172.30.1.86";
//        socket = new Socket(ip, 50001);
//        dis = new DataInputStream(socket.getInputStream());
//        dos = new DataOutputStream(socket.getOutputStream());
//        System.out.println("============ LINE Chat ============");
//        System.out.println("[LINE chat] 환영합니다^^ 연결 되었습니다");
//    }
//
//    public void homePage() throws IOException {
//        static ChatClient chatClient;
//        DataInputStream dis;
//        DataOutputStream dos;
//        String chatName;
//        int roomId;
//        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        static StringBuilder sb;
//        StringTokenizer st;
////    Map<Integer, RoomDTO> rooms = new HashMap<>();
//        String page = "home";  // TODO Enum 작성, home/ room/ chat
//        String id;
//        // receive Thread의 안전한 종료를 위한 filed
//        private volatile boolean running = true;
//        private volatile boolean disconnected = false; // 서버 연결 종료 상태 플래그
//
//
//        //메소드: 서버 연결
//        public void connect() throws IOException {
//            String ip = "192.168.0.25";
////        String ip2 = "192.168.0.4";
//            String ip3 = "172.30.1.86";
//            socket = new Socket(ip, 50001);
//            dis = new DataInputStream(socket.getInputStream());
//            dos = new DataOutputStream(socket.getOutputStream());
//            System.out.println("============ LINE Chat ============");
//            System.out.println("[LINE chat] 환영합니다^^ 연결 되었습니다");
//        }
//
//        public void homePage() throws IOException {
//            while (!disconnected) {  // disconnected 상태가 false일 때만 루프 유지
//                System.out.print("""
//            \n\n========-- Home --========
//            원하는 서비스의 번호를 입력해주세요
//            1. 로그인
//            2. 회원가입
//            3. 종료
//            >> """);
//                String input = br.readLine().strip();
//                if (disconnected) break; // 서버 연결이 종료되면 루프 중단
//
//                switch (input) {
//                    case "1" -> {
//                        if(!login()) continue; // 로그인 요청
//                        synchronized (loginLock) {
//                            try {
//
//                                while (!disconnected) {  // disconnected 상태가 false일 때만 루프 유지
//            System.out.print("""
//            \n\n========-- Home --========
//            원하는 서비스의 번호를 입력해주세요
//            1. 로그인
//            2. 회원가입
//            3. 종료
//            >> """);
//            String input = br.readLine().strip();
//            if (disconnected) break; // 서버 연결이 종료되면 루프 중단
//
//            switch (input) {
//                case "1" -> {
//                    if(!login()) continue; // 로그인 요청
//                    synchronized (loginLock) {
//                        try {
//                            loginLock.wait(); // 로그인 결과 대기
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                        if (!this.isLogin) {
//                            System.out.println("[LINE chat] 로그인에 실패했습니다. 다시 시도해주세요.");
//                            continue;
//                        }
//                    }
//
//                    System.out.println("[LINE chat] 로그인 성공!");
//                    System.out.println("[LINE chat] " + chatName + "님 반갑습니다.");
//                    roomPage();
//                }
//                case "2" -> {
//                    createMember(); // 회원가입 요청
//                    // 회원가입 결과는 createMember()에서 처리됨
//                }
//                case "3" -> {
//                    System.out.println("[LINE chat] 프로그램을 종료합니다. 또 만나요^^");
//                    return;
//                }
//                default -> {
//                    System.out.println("[LINE chat] 유효하지 않은 입력입니다.");
//                }
//            }
//        }
//        System.out.println("[LINE chat] 서버와의 연결이 끊어졌습니다. 프로그램을 종료합니다.");
//        System.exit(0); // 프로그램 전체 종료
//    }
//
//    public boolean login() throws IOException {
//        System.out.print("""
//                \n\n========-- 로그인 --========
//                [LINE chat] 회원가입을 원하시면 'exit' 입력 후, 회원가입 서비스를 이용해주세요
//                ID > """);
//        id = br.readLine().strip();
//        if(id.equals("exit")){
//            return false;
//        }
//
//        System.out.print("PW >");
//        String pw = br.readLine().strip();
//
//        JSONObject jsonObject = makeJsonObject("login");
//        jsonObject.put("pw", pw);
//        jsonObject.put("chatName", "");
//        String json = jsonObject.toString();
////        System.out.println("login 요청 json : " + json);
//
//        chatClient.send(json);
//        return true;
//    }
//
//    boolean isSignupSuccess = false; // 회원가입 성공 여부 확인 변수
//    final Object signupLock = new Object(); // 회원가입 동기화 객체
//
//    public void createMember() throws IOException {
//        System.out.print("""
//            \n\n========-- 회원가입 --========
//            [LINE chat] 로그인을 원하시면 'exit' 입력 후, 로그인 서비스를 이용해주세요
//            ID >  """);
//        String id = br.readLine().strip();
//        if(id.equals("exit")){
//            return;
//        }
//        System.out.print("PW > ");
//        String pw = br.readLine().strip();
//        System.out.print("닉네임 > ");
//        String chatName = br.readLine().strip();
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("command","createMember");
//        jsonObject.put("id", id);
//        jsonObject.put("pw", pw);
//        jsonObject.put("chatName", chatName);
//        jsonObject.put("roomId", roomId);
//
//        send(jsonObject.toString());
//
//        synchronized (signupLock) {
//            try {
//                signupLock.wait(); // 회원가입 결과를 받을 때까지 대기
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if (isSignupSuccess) {
//            System.out.println("\n[LINE chat] 회원가입 성공! 로그인 후 이용해주세요.");
//        } else {
//            System.out.println("\n[LINE chat] 회원가입에 실패했습니다. 다시 시도해주세요.");
//        }
//    }
//
//    //메소드: JSON 받기
//    public void receive() {
//        Thread thread = new Thread(() -> {
//            try {
//                while (running && !disconnected) {  // refactor : running 플래그를 조건으로 사용
//                    try {
//                        String json = dis.readUTF();
//                        JSONObject root = new JSONObject(json);
////                        System.out.println("[Server로부터 왔습니다.] " + root.toString());
//
//                        // 회원 탈퇴 여부 확인
//                        String isDeleteStr = root.optString("isDelete", "false");
//                        boolean isDelete = Boolean.parseBoolean(isDeleteStr);
//
//                        synchronized (deleteLock) {
//                            this.isDeleted = isDelete;
//                            deleteLock.notify(); // 성공 또는 실패 상관없이 알림
//                        }
//
//                        String isModifyNameStr = root.optString("modifyName", "false");
//                        boolean modifyNameResult = Boolean.parseBoolean(isModifyNameStr);
//
//                        synchronized (modifyNameLock) {
//                            if (modifyNameResult) {
//                                // 닉네임 수정 성공 시
//                                this.isNameModified = true;
//                                this.chatName = root.getString("chatName");
//                                modifyNameLock.notify(); // 성공 시 스레드 진행을 알림
//                            } else if (root.has("modifyName") && !modifyNameResult) {
//                                // 요청을 보낸 상태에서 실패만 처리하도록 조건 설정
//                                this.isNameModified = false;
//                                modifyNameLock.notify(); // 실패 시에도 스레드 진행을 알림
//                            }
//                        }
//
//
//                        // 회원가입 결과 처리
//                        String createMemberStr = root.optString("createMember", "false");
//                        boolean createMember = Boolean.parseBoolean(createMemberStr);
//
//                        if (createMember) {
//                            synchronized (signupLock) {
//                                isSignupSuccess = createMember;
//                                signupLock.notify(); // 회원가입 결과를 알림
//                            }
//                            continue; // 회원가입 결과 처리 후 다음 루프로 이동
//                        }
//
//                        // 로그인 여부 확인
//                        String isLoginStr = root.optString("isLogin", "false");
//                        boolean isLogin = Boolean.parseBoolean(isLoginStr);
//                        String senderChatName = root.optString("chatName", null);
//
//                        synchronized (loginLock) {
//                            if (!this.isLogin && isLogin) {
//                                this.isLogin = isLogin;
//                                this.id = root.optString("id", null);
//                                this.chatName = senderChatName;
//                                rooms = getMapFromJsonObject(root.optJSONObject("rooms"));
//                                loginLock.notify(); // 로그인 성공을 알림
//                            } else if (!isLogin) {
//                                this.isLogin = false;
//                                System.out.println("[LINE chat] 비로그인 상태, 로그인 해주세요!");
//                                loginLock.notify(); // 로그인 실패를 알림
//                            }
//                        }
//
//                        // 방 리스트 확인
//                        if (root.has("rooms")) {
//                            rooms = getMapFromJsonObject(root.getJSONObject("rooms"));
//                        }
//
//                        // 메시지 처리
//                        if (root.has("message")) {
//                            int roomId = root.getInt("roomId");
//                            if(roomId == this.roomId){
//                                String message = root.getString("message");
//                                System.out.println("[" + senderChatName + "] " + message);
//                            } else if (roomId == -1) {
//                                this.roomId = -1;
//                            }
//                        }
//                        // 알람 처리
//                        if(root.has("alarm")) {
//                            String alarm = root.getString("alarm");
//                            if (!alarm.isEmpty()) {
//                                System.out.println("[LINE chat] " + alarm);
//                            }
//                        }
//                    } catch (Exception innerException) {
//                        System.out.println("[LINE chat] JSON 처리 중 오류 발생: " + innerException.getMessage());
////                        innerException.printStackTrace();
//                        disconnected = true;
//                        running = false;
//                        break;
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println("[LINE chat] 서버 연결이 끊겼습니다. 관리자에게 문의해주세요.");
//                e.printStackTrace();
//                System.exit(0);
//            } finally {
//                try{
//                    if(dis!=null) dis.close();
//                    if (dos != null) dos.close();
//                    if (socket != null && !socket.isClosed()) socket.close();
//                } catch (IOException e){
//                    System.out.println("[LINE chat] 리소스 해제 중 오류 발생 " + e.getMessage());
//                }
//            }
//        });
//        thread.start();
//    }
//
//    // 방 선택 화면
//    private void roomPage() throws IOException {
//        page = "room";
//        this.isDeleted = false;
//        while (true) {
//            send(makeJsonObject("roomList").toString());
//            sb = new StringBuilder();
////            sb.append("\n\n========-- 채팅방 리스트 --========\n").append("번호\t 채팅방 이름\t\t 인원\n");
//            sb.append("\n\n========-- 채팅방/ 마이페이지 --========\n");
//            sb.append("-- 채팅방 리스트 --\t\t\n");
////            System.out.println(rooms);
//            for (RoomDTO room : rooms.values()) {
//                sb.append(room.getRoomId()).append("\t").append(room.getName()).append("\t")
//                        .append(room.getCountMember()).append("명\n");
//            }
//            sb.append("\n--   마이페이지  --\t\t\n");
//            sb.append("| 11. 닉네임 수정 |\n"); sb.append("| 12. 회원 탈퇴   |\n").append("-----------------\n");
//            sb.append("[LINE chat] Home으로 돌아가려면 'exit'을 입력해주세요\n");
//            System.out.println(sb.toString());
//
//            // 초기화 코드
//            this.isDeleted = false;
//
//            RoomDTO checkedRoom = checkRoomId();
//            if (this.page.equals("home") || isDeleted) {
//                this.isDeleted = false;  // 플래그 초기화
//                break;
//            }
//
//            if (checkedRoom == null) {
//                continue;
//            }
//            chatPage(checkedRoom);
//
//
//            chatPage(checkedRoom);
//        }
//    }
//
//    private JSONObject makeJsonObject(String command) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("command", command);
//        jsonObject.put("id", id);
//        jsonObject.put("roomId", roomId);
//        jsonObject.put("chatName", chatName);
//
//        return jsonObject;
//    }
//
//    private JSONObject makeJsonObject(String command, String message) {
//        JSONObject jsonObject = makeJsonObject(command);
//        jsonObject.put("message", message);
//        return jsonObject;
//    }
//
//    private void chatPage(RoomDTO roomDTO) throws IOException {
//        // 서버에 채팅방 입장 요청
//        // TODO : 이전 10개 채팅 출력
//        page = "chat";
//        this.roomId = roomDTO.getRoomId();
//        System.out.println("\n\n====-- " + roomDTO.getName() + " --====");
//        System.out.println("[LINE chat] 입장 성공! 메시지를 자유롭게 입력해주세요!");
//        System.out.println("[LINE chat] 다른 채팅방 리스트를 보려면 'exit'을 입력해주세요");
////        System.out.println("room 정보 : " + roomDTO);
//        send(makeJsonObject("incoming").toString());  // 입장 알리기
//        while (true) {
//
//            System.out.println();
//            String input = br.readLine().strip();
//            if (input.equals("exit")) {
//                send(makeJsonObject("leave").toString());
//                this.page = "room";
//                break;
//            }
//
//            JSONObject jsonObject = makeJsonObject("message", input);
//            send(jsonObject.toString());
//        }
//    }
//
//    private RoomDTO checkRoomId() throws IOException {
//        RoomDTO roomDTO = null;
//        while (true) {
//            try {
//                System.out.print("입장할 페이지 > ");
//                String input = br.readLine().strip();
//                if (input.equals("exit")) {
//                    this.page = "home";
//                    isLogin = false;
//                    id = null;
//                    return null;
//                }
//                int inputRoomId = Integer.parseInt(input);
//                if(inputRoomId == 11){  // TODO Enum
//                    modifyName();
//                    return null;
//                } else if(inputRoomId == 12){
//                    deleteMember();
//                    return null;
//                }
//                roomDTO = rooms.get(inputRoomId);
//                if (roomDTO == null) {  // 유효하지 않은 입력
//                    System.out.println("[LINE chat] 존재하지 않는 번호 입니다. 다시 입력해주세요");
//                } else {
//                    break;
//                }
//
//            } catch (NumberFormatException e) {
//                System.out.println("[LINE chat] 유효한 번호를 다시 입력해주세요 8ㅅ8");
//            }
//        }
//        return roomDTO;
//    }
//
//    private void deleteMember() throws IOException {
//        System.out.print("""
//        \n\n========-- 회원 탈퇴 --========
//        [LINE chat] 뒤로 가기 원하시면 'exit' 입력하세요
//        """);
//
//        System.out.print("비밀번호 > ");
//        String pw = br.readLine().strip();
//        if (pw.equals("exit")) {
//            return;
//        }
//
//        JSONObject jsonObject = makeJsonObject("deleteMember");
//        jsonObject.put("pw", pw);
//        String json = jsonObject.toString();
//
//        while (true) {
//            System.out.println("[LINE chat] " + chatName + "님, 정말 탈퇴하시겠습니까? (Y/N)");
//            String reCheck = br.readLine().strip().toLowerCase();
//            if (reCheck.equals("n")) {
//                return;
//            } else if (reCheck.equals("y")) {
//                break;
//            }
//        }
//
//        chatClient.send(json);
//
//        synchronized (deleteLock) {
//            try {
//                deleteLock.wait(); // 회원탈퇴 결과를 받을 때까지 대기
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // 회원 탈퇴 결과 확인
//        if (this.isDeleted) {
//            System.out.println("[LINE chat] 회원 탈퇴가 성공적으로 처리되었습니다.");
//        } else {
//            System.out.println("[LINE chat] 회원 탈퇴에 실패했습니다. 다시 시도해주세요.");
//        }
//    }
//
//    private void modifyName() throws IOException {
//        System.out.println("""
//            \n\n========-- 닉네임 수정 --========
//            [LINE chat] 뒤로 가기 원하시면 'exit' 입력하세요
//            """);
//
//        System.out.print("비밀번호 > ");
//        String pw = br.readLine().strip();
//        if (pw.equals("exit")) {
//            return;
//        }
//        System.out.print("새로운 닉네임 > ");
//        String newName = br.readLine().strip();
//
//        JSONObject jsonObject = makeJsonObject("modifyName");
//        jsonObject.put("pw", pw);
//        jsonObject.put("chatName", newName);
//        String json = jsonObject.toString();
////        System.out.println("modifyName 요청 json : " + json);
//
//        chatClient.send(json);
//
//        // 닉네임 변경 결과 대기
//        synchronized (modifyNameLock) {
//            try {
//                modifyNameLock.wait(); // 닉네임 수정 결과를 기다림
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if (this.isNameModified) {
//            System.out.println("[LINE chat] 닉네임이 성공적으로 수정되었습니다.");
//        } else {
//            System.out.println("[LINE chat] 닉네임 수정에 실패했습니다. 다시 시도해주세요.");
//        }
//    }
//
//    /**
//     * @return Map<String, RoomDTO>
//     * @apiNote JSONObject를 Map<String, RoomDTO> 형식으로 변환처리.
//     **/
//    public static Map<Integer, RoomDTO> getMapFromJsonObject(JSONObject jsonObj) {
//        Map<Integer, RoomDTO> map = new HashMap<>();
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            // JSON을 Map<Integer, RoomDTO>로 변환
//            map = mapper.readValue(jsonObj.toString(), new TypeReference<Map<Integer, RoomDTO>>() {
//            });
//        } catch (JsonParseException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
//
//
//    //메소드: JSON 보내기
//    public void send(String json) throws IOException {
//        dos.writeUTF(json);
//        dos.flush();
//    }
//
//    //메소드: 서버 연결 종료
//    public void unconnect() throws IOException {
//        running = false; // 종료 신호 설정
//        try {
//            if (socket != null && !socket.isClosed()) {
//                socket.close();
//            }
//        } catch (IOException e) {
//            System.out.println("[LINE chat] 서버 연결 해제 중 오류가 발생했습니다: " + e.getMessage());
//        }
//    }
//
//    //메소드: 메인
//// 메인 메서드의 unconnect 호출 위치 변경
//    public static void main(String[] args) {
//        try {
//            chatClient = new ChatClient();
//            chatClient.connect();
//            chatClient.receive();  // receive 스레드 시작
//            chatClient.homePage(); // 홈 페이지로 이동, 로그인, 회원가입 처리
//        } catch (Exception e) {
//            System.out.println("[LINE chat] 서버 연결에 실패하였습니다.");
//            e.printStackTrace();
//        } finally {
//            try {
//                br.close();
//                if (chatClient != null) {
//                    chatClient.unconnect(); // 서버 연결 해제
//                }
//            } catch (IOException e) {
//                System.out.println("[LINE chat] 자원 해제 중 오류 발생");
//                e.printStackTrace();
//            }
//        }
//    }
//}