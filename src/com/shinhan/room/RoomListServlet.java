package com.shinhan.room;

import com.shinhan.room.model.RoomDTO;
import com.shinhan.room.model.RoomService;
import java.util.List;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/room/rooms.do")
public class RoomListServlet extends HttpServlet {
    RoomService roomService = new RoomService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<RoomDTO> allRooms = roomService.getAllRooms();
        System.out.println("rooms : " + allRooms);
        request.setAttribute("rooms", allRooms);
        request.getRequestDispatcher("rooms.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
