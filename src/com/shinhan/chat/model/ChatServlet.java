package com.shinhan.chat.model;

import com.shinhan.room.model.RoomDTO;
import com.shinhan.room.model.RoomService;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/chat/chat.do")
public class ChatServlet extends HttpServlet {
    RoomService roomService = new RoomService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        System.out.println("Chat Get method");
        String roomId = request.getParameter("chatId");
        request.setAttribute("roomId", roomId);

        RoomDTO room = roomService.getRoomById(Integer.parseInt(roomId));
        request.setAttribute("room", room);

        HttpSession session = request.getSession();
        request.setAttribute("member", session.getAttribute("member"));
        request.getRequestDispatcher("/chat/chat.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
