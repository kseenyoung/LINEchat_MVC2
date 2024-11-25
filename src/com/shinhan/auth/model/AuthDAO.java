package com.shinhan.auth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.shinhan.util.DBUtil;

public class AuthDAO {
        public MemberDTO login(MemberDTO memberDTO) {
            String login_sql = "select * from members where member_id = ?";
            Connection conn = DBUtil.getConnection2();
            PreparedStatement st=null;
            ResultSet rs = null;
            MemberDTO result = null;

            try {
                st = conn.prepareStatement(login_sql);
                st.setString(1, memberDTO.getMemberId());
                rs = st.executeQuery();
                while(rs.next()){
                    // 아이디 있음
                    String userPass = rs.getNString("password");

                    if(memberDTO.getPassword().equals(userPass)){
                        // 비밀번호 맞음
                        result = MemberDTO.builder()
                                .memberId(memberDTO.getMemberId())
                                .password(memberDTO.getPassword())
                                .name(rs.getString("name"))
                                .createdAt(rs.getTimestamp("created_at"))
                                .updatedAt(rs.getTimestamp("updated_at"))
                                .build();
                    } else{
                        result = MemberDTO.builder().memberId("-1").build();
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DBUtil.dbDisconnection(conn, st, rs);
            }

            return result;
        }

        public boolean signup(MemberDTO memberDTO) {
            String login_sql = "insert into members(member_id, name, password) values(?, ?, ?)";
            Connection conn = DBUtil.getConnection2();
            PreparedStatement st=null;
            ResultSet rs = null;

            try {
                st = conn.prepareStatement(login_sql);
                st.setString(1, memberDTO.getMemberId());
                st.setString(2, memberDTO.getName());
                st.setString(3, memberDTO.getPassword());
                int result = st.executeUpdate();
                if(result > 0) return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DBUtil.dbDisconnection(conn, st, rs);
            }

            return false;
        }

        public boolean modifyName(MemberDTO memberDTO) {
            String modify_sql = "update members set name =? where member_id=?";
            Connection conn = DBUtil.getConnection2();
            PreparedStatement st=null;
            ResultSet rs = null;

            try {
                st = conn.prepareStatement(modify_sql);
                st.setString(1, memberDTO.getName());
                st.setString(2, memberDTO.getMemberId());
                int result = st.executeUpdate();
                if(result > 0) return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DBUtil.dbDisconnection(conn, st, rs);
            }

            return false;
        }

        public boolean deleteMember(MemberDTO memberDTO) {
            String delete_sql = "delete from members where member_id = ? and password= ?";
            Connection conn = DBUtil.getConnection2();
            PreparedStatement st=null;
            ResultSet rs = null;

            try {
                st = conn.prepareStatement(delete_sql);
                st.setString(1, memberDTO.getMemberId());
                st.setString(2, memberDTO.getPassword());
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
