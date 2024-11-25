package com.shinhan.auth;

import com.shinhan.auth.model.AuthService;
import com.shinhan.auth.model.MemberDTO;
import com.shinhan.room.model.RoomDTO;
import com.shinhan.room.model.RoomService;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth/update.do")
public class UpdateServlet extends HttpServlet {

    AuthService authService = new AuthService();
    RoomService roomService = new RoomService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        member.setName(request.getParameter("modify_name"));

        boolean b = authService.modifyName(member);
        session.setAttribute("member", member);
        request.setAttribute("isModify", b);

        List<RoomDTO> allRooms = roomService.getAllRooms();
        System.out.println("rooms : " + allRooms);
        request.setAttribute("rooms", allRooms);
        request.getRequestDispatcher("/room/rooms.jsp").forward(request, response);
    }
}
