package com.shinhan.filter;

import com.shinhan.auth.model.MemberDTO;
import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private List<String> whiteList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("[filter] 생성 시 수행");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (whiteList == null){
            SessionCheckFilter(req);
        }
        // 모든 마다 수행된다.
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");


        // 로그인 확인
        System.out.println(req.getRequestURI());
        if(!whiteList.contains(req.getRequestURI())){
            HttpSession session = req.getSession();
            MemberDTO member = (MemberDTO) session.getAttribute("member");

            if(member == null){
                System.out.println("로그인 안 한 사용자");
                System.out.println(whiteList);
                res.sendRedirect(req.getContextPath()+"/auth/login.do");
                return;
            }
        }

        // 서블릿 요청 before
//        System.out.println("--- servlet 요청 before");

        try{
            filterChain.doFilter(request, response);  // 다른 필터를 수행하거나 서블릿을 수행하러 간다.
        } catch (Throwable e){
            request.getRequestDispatcher("/error/error500.jsp").forward(request, response);
        }
        // 서블릿 요청 after
//        System.out.println("--- servlet 요청 after, 응답하기 전");
    }

    public void SessionCheckFilter(HttpServletRequest request){
        whiteList = new ArrayList<String>();
        whiteList.add(request.getContextPath());
        whiteList.add(request.getContextPath()+"/auth/login.do");
        whiteList.add(request.getContextPath()+"/auth/signup.do");
        whiteList.add(request.getContextPath()+"/auth/css/home.css");
        whiteList.add(request.getContextPath()+"/auth/js/home.js");

        whiteList.add(request.getContextPath()+"/room/css/rooms.css");
        whiteList.add(request.getContextPath()+"/room/js/rooms.js");

        whiteList.add(request.getContextPath()+"/chat/css/chat.css");
        whiteList.add(request.getContextPath()+"/chat/js/chat.js");

        whiteList.add(request.getContextPath()+"/images/line.png");
    }

    @Override
    public void destroy() {
//        System.out.println("[filter] 소멸 시 수행");
    }
}
