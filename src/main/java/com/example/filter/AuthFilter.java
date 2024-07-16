package com.example.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(AuthFilter.URL)
public class AuthFilter extends HttpFilter {

    public static final String URL = "/users/*";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect("/login.jsp");
        } else {
            chain.doFilter(req, res);
        }
    }
}