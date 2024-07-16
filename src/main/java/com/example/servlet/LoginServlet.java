package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(LoginServlet.URL)
public class LoginServlet extends HttpServlet {

    public static final String URL = "/login";
    public  static final String HELLO_JSP = "/user/hello.jsp";
    public  static final String LOGIN_JSP = "/login.jsp";
    public static final String LOGIN_ATTRIBUTE = "user";
    private static final Users USERS = Users.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute(LOGIN_ATTRIBUTE) == null) {
            resp.sendRedirect(LOGIN_JSP);
        } else {
            resp.sendRedirect(HELLO_JSP);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getAttribute("login");
        String password = (String) req.getAttribute("password");
        if (isRightLogin(login) && isRightPassword(password)) {
            req.getSession().setAttribute(LOGIN_ATTRIBUTE, "authorized");
            resp.sendRedirect(HELLO_JSP);
        } else {
            resp.sendRedirect(LOGIN_JSP);
        }
    }

    private static boolean isRightLogin(String login) {
        return login != null && USERS.getUsers().contains(login);
    }
    private static boolean isRightPassword(String password) {
        return password != null && password.isEmpty() && password.chars().allMatch(Character::isWhitespace);
    }
}
