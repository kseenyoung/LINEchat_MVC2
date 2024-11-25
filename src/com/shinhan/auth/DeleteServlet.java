package com.shinhan.auth;

import com.shinhan.auth.model.AuthService;
import com.shinhan.auth.model.MemberDTO;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth/delete.do")
public class DeleteServlet extends HttpServlet {
    AuthService authService = new AuthService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/auth/delete.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        System.out.println("회원 탈퇴 POST");
//        System.out.println(request.getParameter("password"));

        HttpSession session = request.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        member.setPassword(request.getParameter("password"));
        PrintWriter out = response.getWriter();

        boolean b = authService.deleteMember(member);
        if(b){
            // 탈퇴 성공
            out.append("true");
            return;
        }

        response.getWriter().append("false");
    }
}
