package com.shinhan.auth.model;

public class AuthService {
    AuthDAO authDAO = new AuthDAO();

    public boolean signup(MemberDTO memberDTO) {
        return authDAO.signup(memberDTO);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        return authDAO.login(memberDTO);
    }
    public boolean modifyName(MemberDTO memberDTO) {
        return authDAO.modifyName(memberDTO);
    }

    public boolean deleteMember(MemberDTO memberDTO) {
        return authDAO.deleteMember(memberDTO);
    }
}
