package Servlet;

import DatabaseDAO.UserDAO;
import Bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        if (identifyCode(request, response)) {
            User user = new User();
            user.setUserName(request.getParameter("name"));
            user.setPass(request.getParameter("password"));
            User user1 = userDAO.checkLogin(user);
            if (user1 == null) {
                printWriter.write("fail");
            } else {
                request.getSession().setAttribute("user", user1);
                printWriter.write("success");
            }
        } else {
            printWriter.write("code");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean identifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        // 验证验证码
        String sessionCode = request.getSession().getAttribute("code").toString();
        if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
            if (code.equalsIgnoreCase(sessionCode)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
