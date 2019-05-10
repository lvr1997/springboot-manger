package com.lvr.springbootmanger.utils;

import com.lvr.springbootmanger.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session= request.getSession();
        User user= (User) session.getAttribute("userInfo");
        String uri= request.getRequestURI();
        if(uri.contains("/login") ||uri.contains("/assets")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            if(user!=null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                request.getRequestDispatcher("/login").forward(servletRequest,servletResponse);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
