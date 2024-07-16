package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(LogoutServlet.URL)
public class LogoutServlet extends HttpServlet {
    public static final String URL = "/logout";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute(LoginServlet.LOGIN_ATTRIBUTE);
        session.invalidate();
        resp.sendRedirect(LoginServlet.LOGIN_JSP);
    }
}
