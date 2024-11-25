package com.shinhan.room.model;

import java.util.List;
import java.util.Map;

public class RoomService {
    RoomDAO roomDAO = new RoomDAO();
    public List<RoomDTO> getAllRooms() {
        return roomDAO.getAllRooms();
    }
    public RoomDTO getRoomById(int roomId) {return  roomDAO.getRoomById(roomId);}
}
