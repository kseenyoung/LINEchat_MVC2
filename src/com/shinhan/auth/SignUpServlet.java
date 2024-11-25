package com.shinhan.auth;

import com.shinhan.auth.model.AuthService;
import com.shinhan.auth.model.MemberDTO;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth/signup.do")
public class SignUpServlet extends HttpServlet {
    AuthService authService = new AuthService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(request.getParameter("member_id"))
                .password(request.getParameter("member_password"))
                .name(request.getParameter("member_name"))
                .build();
        System.out.println(memberDTO);

        boolean result = authService.signup(memberDTO);
        System.out.println("sign up result : " + result);
        if(result){
            request.setAttribute("signup_message", "회원가입 성공! 로그인해주세요");
        } else{
            // TODO 실패 시 처리
        }

//        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
//        rd.forward(request, response);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
