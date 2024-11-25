package com.shinhan.chat.model;

import com.shinhan.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatDAO {
    public boolean insertMessage(ChatDTO chatDTO) {
        String login_sql = "insert into chats(chat_id, room_id, member_id, content) values(chats_seq.nextval, ?, ?, ?)";
        Connection conn = DBUtil.getConnection2();
        PreparedStatement st=null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(login_sql);
            st.setInt(1, chatDTO.getRoom_id());
            st.setString(2, chatDTO.getMember_id());
            st.setString(3, chatDTO.getContent());
            int result = st.executeUpdate();
            if(result > 0) return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnection(conn, st, rs);
        }

        return false;
    }
}
