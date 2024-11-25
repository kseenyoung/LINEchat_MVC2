package com.shinhan.room.model;

import com.shinhan.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomDAO {
    public List<RoomDTO> getAllRooms() {
        String room_select_sql = "select * from rooms";
        Connection conn = DBUtil.getConnection2();
        PreparedStatement st=null;
        ResultSet rs = null;
        List<RoomDTO> rooms = new ArrayList<>();

        try {
            st = conn.prepareStatement(room_select_sql);
            rs = st.executeQuery();
            while(rs.next()){
                RoomDTO roomDTO = RoomDTO.builder()
                        .roomId(rs.getInt("room_id"))
                        .name(rs.getString("name"))
                        .build();
                rooms.add(roomDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnection(conn, st, rs);
        }

        return rooms;
    }

    public RoomDTO getRoomById(int roomId) {
        String room_select_sql = "select * from rooms where room_id=?";
        Connection conn = DBUtil.getConnection2();
        PreparedStatement st=null;
        ResultSet rs = null;
        RoomDTO room = null;

        try {
            st = conn.prepareStatement(room_select_sql);
            st.setInt(1, roomId);
            rs = st.executeQuery();
            while(rs.next()){
                room = RoomDTO.builder()
                        .roomId(rs.getInt("room_id"))
                        .name(rs.getString("name"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .updatedAt(rs.getTimestamp("updated_at"))
                        .build();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnection(conn, st, rs);
        }

        return room;
    }
}