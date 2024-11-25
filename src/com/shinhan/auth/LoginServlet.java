package com.shinhan.auth;

import com.shinhan.auth.model.AuthDAO;
import com.shinhan.auth.model.AuthService;
import com.shinhan.auth.model.MemberDTO;
import com.shinhan.room.model.RoomDTO;
import com.shinhan.room.model.RoomService;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth/login.do")
public class LoginServlet extends HttpServlet {
    AuthService authService = new AuthService();
    RoomService roomService  = new RoomService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        System.out.println("GET login");
        HttpSession session = request.getSession();
        session.invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        System.out.println("로그인 요청 들어옴");
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(request.getParameter("member_id"))
                .password(request.getParameter("member_password"))
                .build();
//        System.out.println(memberDTO);

        MemberDTO result = authService.login(memberDTO);
        String message = null;
        boolean flag = false;

        if(result == null){
            // 아이디 없음
            message = "존재하지 않는 아이디입니다";
            flag = true;
        } else if(result.getMemberId().equals("-1")){
            // 비밀번호 틀림
            message = "비밀번호가 틀렸습니다";
            flag = true;
        }

        if(flag){
            request.setAttribute("message", message);
            request.getRequestDispatcher("home.jsp").forward(request, response);
            return;
        }

        // 로그인 성공
        HttpSession session = request.getSession();
        session.setAttribute("member", result);

//        request.getRequestDispatcher("../room/rooms.jsp").forward(request, response);
        response.sendRedirect("../room/rooms.do");
    }
}
